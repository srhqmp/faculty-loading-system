package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.repo.FacultyRepo;
import edu.university.facultyloading.util.DbConnection;
import edu.university.facultyloading.util.PromptMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FacultyRepoImpl implements FacultyRepo {

    private final DbConnection dbConnection;

    public FacultyRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Faculty create(Faculty faculty) {
        String userQuery = "INSERT INTO tblusers (username, password, first_name, last_name, role) VALUES (?, ?, ?, ?, 1)";
        String facultyQuery = "INSERT INTO tblfaculties (user_id, major, years_of_experience, student_feedback_score, is_available) VALUES (?, ?, ?, ?, 1)";

        try (Connection connection = dbConnection.connect();
                PreparedStatement userStmt = connection.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement facultyStmt = connection.prepareStatement(facultyQuery,
                        Statement.RETURN_GENERATED_KEYS)) {

            // Insert into tblusers
            userStmt.setString(1, faculty.getUsername());
            userStmt.setString(2, faculty.getPassword());
            userStmt.setString(3, faculty.getFirstName());
            userStmt.setString(4, faculty.getLastName());
            userStmt.executeUpdate();

            ResultSet userKeys = userStmt.getGeneratedKeys();
            if (userKeys.next()) {
                int userId = userKeys.getInt(1);
                faculty.setUserId(userId);

                // Insert into tblfaculties
                facultyStmt.setInt(1, userId);
                facultyStmt.setString(2, faculty.getMajor());
                facultyStmt.setInt(3, faculty.getYearsOfExperience());
                facultyStmt.setDouble(4, faculty.getStudentFeedbackScore());
                facultyStmt.executeUpdate();

                ResultSet facultyKeys = facultyStmt.getGeneratedKeys();
                if (facultyKeys.next()) {
                    int facultyId = facultyKeys.getInt(1);
                    faculty.setFacultyId(facultyId);
                }

                return faculty;
            }

        } catch (SQLException e) {
            PromptMessage.errorMessage("FacultyRepoImpl - create(): " + e.getMessage());
        }

        return null;
    }

    @Override
    public Faculty authenticate(String username, String password) {
        String query = "SELECT faculty_id, tblfaculties.user_id AS user_id, username, password, first_name, last_name, major, years_of_experience, student_feedback_score, is_available, is_archived "
                +
                "FROM tblfaculties INNER JOIN tblusers ON tblfaculties.user_id = tblusers.user_id " +
                "WHERE username = ? AND password = ? AND is_archived = 0";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return mapResultSetToFaculty(result);
            }

        } catch (SQLException e) {
            PromptMessage.errorMessage("Faculty Repo - authenticate(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public Faculty getById(int facultyId) {
        String query = "SELECT faculty_id, tblfaculties.user_id AS user_id, username, password, first_name, last_name, major, years_of_experience, student_feedback_score, is_available, is_archived "
                +
                "FROM tblfaculties INNER JOIN tblusers ON tblfaculties.user_id = tblusers.user_id " +
                "WHERE faculty_id = ? AND is_archived = 0";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, facultyId);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return mapResultSetToFaculty(result);
            }

        } catch (SQLException e) {
            PromptMessage.errorMessage("Faculty Repo - getById(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Faculty> getAll() {
        List<Faculty> faculties = new ArrayList<>();
        String query = "SELECT faculty_id, tblfaculties.user_id AS user_id, username, password, first_name, last_name, major, years_of_experience, student_feedback_score, is_available, is_archived "
                +
                "FROM tblfaculties INNER JOIN tblusers ON tblfaculties.user_id = tblusers.user_id " +
                "WHERE tblusers.role = 1 AND is_archived = 0";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                faculties.add(mapResultSetToFaculty(result));
            }

        } catch (SQLException e) {
            PromptMessage.errorMessage("Faculty Repo - getAll(): " + e.getMessage());
        }
        return faculties;
    }

    @Override
    public boolean update(Faculty faculty) {
        String userQuery = "UPDATE tblusers SET username = ?, password = ?, first_name = ?, last_name = ? WHERE user_id = ?";
        String facultyQuery = "UPDATE tblfaculties SET major = ?, years_of_experience = ?, student_feedback_score = ?, is_available = ? WHERE faculty_id = ?";

        try (Connection connection = dbConnection.connect();
                PreparedStatement userStmt = connection.prepareStatement(userQuery);
                PreparedStatement facultyStmt = connection.prepareStatement(facultyQuery)) {

            // Update tblusers
            userStmt.setString(1, faculty.getUsername());
            userStmt.setString(2, faculty.getPassword());
            userStmt.setString(3, faculty.getFirstName());
            userStmt.setString(4, faculty.getLastName());
            userStmt.setInt(5, faculty.getUserId());
            userStmt.executeUpdate();

            // Update tblfaculties
            facultyStmt.setString(1, faculty.getMajor());
            facultyStmt.setInt(2, faculty.getYearsOfExperience());
            facultyStmt.setDouble(3, faculty.getStudentFeedbackScore());
            facultyStmt.setBoolean(4, faculty.isAvailable());
            facultyStmt.setInt(5, faculty.getFacultyId());
            return facultyStmt.executeUpdate() > 0;

        } catch (SQLException e) {
            PromptMessage.errorMessage("Faculty Repo - update(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateAvailability(int facultyId, boolean isAvailable) {
        String query = "UPDATE tblfaculties SET is_available = ? WHERE faculty_id = ?";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, isAvailable ? 1 : 0);
            stmt.setInt(2, facultyId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            PromptMessage.errorMessage("Faculty Repo - updateAvailability(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean archive(int facultyId) {
        String query = "UPDATE tblusers SET is_archived = 1 WHERE user_id = (SELECT user_id FROM tblfaculties WHERE faculty_id = ?)";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, facultyId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            PromptMessage.errorMessage("Faculty Repo - archive(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean restore(int facultyId) {
        String query = "UPDATE tblusers SET is_archived = 0 WHERE user_id = (SELECT user_id FROM tblfaculties WHERE faculty_id = ?)";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, facultyId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            PromptMessage.errorMessage("Faculty Repo - restore(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int facultyId) {
        String query = "DELETE FROM tblusers WHERE user_id = (SELECT user_id FROM tblfaculties WHERE faculty_id = ?)";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, facultyId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            PromptMessage.errorMessage("Faculty Repo - delete(): " + e.getMessage());
        }
        return false;
    }

    private Faculty mapResultSetToFaculty(ResultSet result) throws SQLException {
        int facultyId = result.getInt("faculty_id");
        int userId = result.getInt("user_id");
        String username = result.getString("username");
        String password = result.getString("password");
        String firstName = result.getString("first_name");
        String lastName = result.getString("last_name");
        String major = result.getString("major");
        int yearsOfExperience = result.getInt("years_of_experience");
        double studentFeedbackScore = result.getDouble("student_feedback_score");
        boolean isAvailable = result.getInt("is_available") == 1;

        return new Faculty(facultyId, userId, username, password, firstName, lastName, major,
                yearsOfExperience, studentFeedbackScore, isAvailable);
    }

}