package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Load;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.LoadRepo;
import edu.university.facultyloading.util.DbConnection;
import edu.university.facultyloading.util.PromptMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoadRepoImpl implements LoadRepo {

    private final DbConnection dbConnection;

    public LoadRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean create(Load load) {
        String checkQuery = "SELECT COUNT(*) FROM tblloads WHERE faculty_id = ? AND subject_id = ?";
        String insertQuery = "INSERT INTO tblloads (faculty_id, subject_id) VALUES (?, ?)";

        try (Connection connection = dbConnection.connect();
                PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
                PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {

            checkStmt.setInt(1, load.getFacultyId());
            checkStmt.setInt(2, load.getSubjectId());

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return false; // Prevent duplicate
            }

            insertStmt.setInt(1, load.getFacultyId());
            insertStmt.setInt(2, load.getSubjectId());

            return insertStmt.executeUpdate() > 0;

        } catch (SQLException e) {
            PromptMessage.errorMessage("LoadRepo - create(): " + e.getMessage());
        }

        return false;
    }

    @Override
    public Load getById(int loadId) {
        String query = "SELECT load_id, faculty_id, subject_id FROM tblloads WHERE load_id = ? AND is_archived = 0";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, loadId);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new Load(
                        result.getInt("load_id"),
                        result.getInt("faculty_id"),
                        result.getInt("subject_id"));
            }
        } catch (SQLException e) {
            PromptMessage.errorMessage("LoadRepo - getById(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Load> getAll() {
        List<Load> loads = new ArrayList<>();
        String query = "SELECT load_id, faculty_id, subject_id FROM tblloads WHERE is_archived = 0";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                loads.add(new Load(
                        result.getInt("load_id"),
                        result.getInt("faculty_id"),
                        result.getInt("subject_id")));
            }
        } catch (SQLException e) {
            PromptMessage.errorMessage("LoadRepo - getAll(): " + e.getMessage());
        }
        return loads;
    }

    @Override
    public List<Load> getByFacultyId(int facultyId) {
        List<Load> loads = new ArrayList<>();
        String query = "SELECT load_id, faculty_id, subject_id FROM tblloads WHERE faculty_id = ? AND is_archived = 0";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, facultyId);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                loads.add(new Load(
                        result.getInt("load_id"),
                        result.getInt("faculty_id"),
                        result.getInt("subject_id")));
            }
        } catch (SQLException e) {
            PromptMessage.errorMessage("LoadRepo - getByFacultyId(): " + e.getMessage());
        }
        return loads;
    }

    @Override
    public boolean update(Load load) {
        String query = "UPDATE tblloads SET faculty_id = ?, subject_id = ? WHERE load_id = ?";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, load.getFacultyId());
            stmt.setInt(2, load.getSubjectId());
            stmt.setInt(3, load.getLoadId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            PromptMessage.errorMessage("LoadRepo - update(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean archive(int loadId) {
        String query = "UPDATE tblloads SET is_archived = 1 WHERE load_id = ?";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, loadId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            PromptMessage.errorMessage("LoadRepo - archive(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean restore(int loadId) {
        String query = "UPDATE tblloads SET is_archived = 0 WHERE load_id = ?";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, loadId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            PromptMessage.errorMessage("LoadRepo - restore(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int loadId) {
        String query = "DELETE FROM tblloads WHERE load_id = ?";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, loadId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            PromptMessage.errorMessage("LoadRepo - delete(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean assignSubjectToFaculty(int facultyId, int subjectId) {
        String checkQuery = "SELECT load_id FROM tblloads WHERE faculty_id = ? AND subject_id = ?";
        String insertQuery = "INSERT INTO tblloads (faculty_id, subject_id) VALUES (?, ?)";

        try (Connection connection = dbConnection.connect()) {
            // Check if already assigned
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, facultyId);
                checkStmt.setInt(2, subjectId);
                ResultSet result = checkStmt.executeQuery();
                if (result.next()) {
                    // Already assigned
                    return false;
                }
            }
            // Assign new load
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, facultyId);
                insertStmt.setInt(2, subjectId);
                return insertStmt.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            PromptMessage.errorMessage("LoadRepo - assignSubjectToFaculty(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Subject> getSubjectsByFacultyId(int facultyId) {
        List<Subject> subjects = null;
        String query = "SELECT tblsubjects.subject_id, tblsubjects.name, tblsubjects.description, " +
                "tblsubjects.recommended_major, tblsubjects.complexity_level " +
                "FROM tblloads " +
                "INNER JOIN tblsubjects ON tblloads.subject_id = tblsubjects.subject_id " +
                "WHERE tblloads.faculty_id = ? " +
                "AND tblsubjects.is_archived = 0";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, facultyId);
            ResultSet result = stmt.executeQuery();
            subjects = new ArrayList<>();
            while (result.next()) {
                Subject subject = new Subject(
                        result.getInt("subject_id"),
                        result.getString("name"),
                        result.getString("description"),
                        result.getString("recommended_major"),
                        result.getInt("complexity_level"));
                subjects.add(subject);
            }

        } catch (SQLException e) {
            PromptMessage.errorMessage("LoadRepo - getSubjectsByFacultyId(): " + e.getMessage());
        }

        return subjects;
    }

    @Override
    public List<Faculty> getAvailableFacultiesBySubjectId(int subjectId) {
        List<Faculty> faculties = new ArrayList<>();

        String query = "SELECT tblfaculties.faculty_id, tblusers.user_id, tblusers.username, " +
                "tblusers.first_name, tblusers.last_name, " +
                "tblfaculties.is_available " +
                "FROM tblloads " +
                "INNER JOIN tblfaculties ON tblloads.faculty_id = tblfaculties.faculty_id " +
                "INNER JOIN tblusers ON tblfaculties.faculty_id = tblusers.user_id " +
                "INNER JOIN tblsubjects ON tblloads.subject_id = tblsubjects.subject_id " +
                "WHERE tblloads.subject_id = ? " +
                "AND tblfaculties.is_available = 1 " +
                "AND tblusers.is_archived = 0 " +
                "AND tblsubjects.is_archived = 0";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, subjectId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Faculty faculty = new Faculty();
                faculty.setFacultyId(rs.getInt("faculty_id"));
                faculty.setUserId(rs.getInt("user_id"));
                faculty.setUsername(rs.getString("username"));
                faculty.setFirstName(rs.getString("first_name"));
                faculty.setLastName(rs.getString("last_name"));
                faculty.setAvailable(rs.getInt("is_available") == 1);

                faculties.add(faculty);
            }

        } catch (SQLException e) {
            PromptMessage.errorMessage("LoadRepo - getAvailableFacultiesBySubjectId(): " + e.getMessage());
        }

        return faculties;
    }

    @Override
    public boolean removeSubjectFromFaculty(int facultyId, int subjectId) {
        String deleteQuery = "DELETE FROM tblloads WHERE faculty_id = ? AND subject_id = ?";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(deleteQuery)) {

            stmt.setInt(1, facultyId);
            stmt.setInt(2, subjectId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            PromptMessage.errorMessage("LoadRepo - removeSubjectFromFaculty(): " + e.getMessage());
        }
        return false;
    }
}
