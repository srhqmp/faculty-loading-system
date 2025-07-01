package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.repo.FacultyRepo;
import edu.university.facultyloading.util.DbConnection;
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
    public void create(Faculty faculty) {
        String userQuery = "INSERT INTO tblusers (username, password, first_name, last_name, role) VALUES (?, ?, ?, ?, 1)";
        String facultyQuery = "INSERT INTO tblfaculties (user_id, major, years_of_experience, student_feedback_score, is_available) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dbConnection.connect();
                PreparedStatement userStmt = connection.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS)) {

            userStmt.setString(1, faculty.getUsername());
            userStmt.setString(2, faculty.getPassword());
            userStmt.setString(3, faculty.getFirstName());
            userStmt.setString(4, faculty.getLastName());
            userStmt.executeUpdate();

            ResultSet keys = userStmt.getGeneratedKeys();
            if (keys.next()) {
                int userId = keys.getInt(1);
                try (PreparedStatement facultyStmt = connection.prepareStatement(facultyQuery)) {
                    facultyStmt.setInt(1, userId);
                    facultyStmt.setString(2, faculty.getMajor());
                    facultyStmt.setInt(3, faculty.getYearsOfExperience());
                    facultyStmt.setDouble(4, faculty.getStudentFeedbackScore());
                    facultyStmt.setInt(5, faculty.isAvailable() ? 1 : 0);
                    facultyStmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println("Faculty Repo - create(): " + e.getMessage());
        }
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
            System.out.println("Faculty Repo - authenticate(): " + e.getMessage());
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
            System.out.println("Faculty Repo - getById(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Faculty> getAll() {
        List<Faculty> faculties = new ArrayList<>();
        String query = "SELECT faculty_id, tblfaculties.user_id AS user_id, username, password, first_name, last_name, major, years_of_experience, student_feedback_score, is_available, is_archived "
                +
                "FROM tblfaculties INNER JOIN tblusers ON tblfaculties.user_id = tblusers.user_id " +
                "WHERE is_archived = 0";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                faculties.add(mapResultSetToFaculty(result));
            }

        } catch (SQLException e) {
            System.out.println("Faculty Repo - getAll(): " + e.getMessage());
        }
        return faculties;
    }

    @Override
    public void update(Faculty faculty) {
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
            facultyStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Faculty Repo - update(): " + e.getMessage());
        }
    }

    @Override
    public void updateAvailability(int facultyId, boolean isAvailable) {
        String query = "UPDATE tblfaculties SET is_available = ? WHERE faculty_id = ?";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, isAvailable ? 1 : 0);
            stmt.setInt(2, facultyId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Faculty Repo - updateAvailability(): " + e.getMessage());
        }
    }

    @Override
    public void archive(int facultyId) {
        String query = "UPDATE tblusers SET is_archived = 1 WHERE user_id = (SELECT user_id FROM tblfaculties WHERE faculty_id = ?)";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, facultyId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Faculty Repo - archive(): " + e.getMessage());
        }
    }

    @Override
    public void restore(int facultyId) {
        String query = "UPDATE tblusers SET is_archived = 0 WHERE user_id = (SELECT user_id FROM tblfaculties WHERE faculty_id = ?)";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, facultyId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Faculty Repo - restore(): " + e.getMessage());
        }
    }

    @Override
    public void delete(int facultyId) {
        String query = "DELETE FROM tblusers WHERE user_id = (SELECT user_id FROM tblfaculties WHERE faculty_id = ?)";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, facultyId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Faculty Repo - delete(): " + e.getMessage());
        }
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
        boolean isAvailable = result.getBoolean("is_available");

        return new Faculty(facultyId, userId, username, password, firstName, lastName, major,
                yearsOfExperience, studentFeedbackScore, isAvailable);
    }

}