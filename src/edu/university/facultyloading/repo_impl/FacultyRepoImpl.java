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

    private final DbConnection dbConnection; // composition

    // constructor injection
    public FacultyRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Faculty fetchFaculty(int facultyId) {
        String query = "SELECT * FROM tblfaculties "
                + "INNER JOIN tblusers "
                + "ON tblfaculties.user_id = tblusers.user_id "
                + "WHERE faculty_id = ? "
                + "AND is_archived = 0";

        try (Connection connnection = dbConnection.connect();
                PreparedStatement preparedState = connnection.prepareStatement(query);) {

            preparedState.setInt(1, facultyId);
            ResultSet result = preparedState.executeQuery();

            if (result.next()) {
                Faculty faculty = new Faculty();

                int id = result.getInt("user_id");
                String username = result.getString("username");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                int role = result.getInt("role");
                // faculty
                String major = result.getString("major");
                int yearsOfExperience = result.getInt("years_of_experience");
                double studentFeedbackScore = result.getDouble("student_feedback_score");
                int isAvailable = result.getInt("is_available");

                faculty.setId(id);
                faculty.setFacultyId(facultyId);
                faculty.setUsername(username);
                faculty.setFirstName(firstName);
                faculty.setLastName(lastName);
                faculty.setRole(role);
                faculty.setMajor(major);
                faculty.setYearsOfExperience(yearsOfExperience);
                faculty.setStudentFeedbackScore(studentFeedbackScore);
                faculty.setIsAvailable(isAvailable);

                return faculty;
            }
        } catch (SQLException e) {
            System.out.println("Admin Repo - fetchFaculty(): " + e.getMessage());
        }

        return null;
    }

    @Override
    public Faculty fetchFaculty(String username, String password) {
        String query = "SELECT faculty_id, tblusers.user_id, first_name, last_name, major, years_of_experience, student_feedback_score, is_available "
                + "FROM tblfaculties "
                + "INNER JOIN tblusers "
                + "ON tblfaculties.user_id = tblusers.user_id "
                + "WHERE username = ? "
                + "AND password = ? "
                + "AND role = 1 "
                + "AND is_archived = 0";

        try (Connection connnection = dbConnection.connect();
                PreparedStatement preparedState = connnection.prepareStatement(query);) {

            preparedState.setString(1, username);
            preparedState.setString(2, password);
            ResultSet result = preparedState.executeQuery();

            if (result.next()) {
                Faculty faculty = new Faculty();

                int id = result.getInt("user_id");
                int facultyId = result.getInt("faculty_id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String major = result.getString("major");
                int yearsOfExperience = result.getInt("years_of_experience");
                double studentFeedbackScore = result.getDouble("student_feedback_score");
                int isAvailable = result.getInt("is_available");

                faculty.setFacultyId(facultyId);
                faculty.setId(id);
                faculty.setUsername(username);
                faculty.setFirstName(firstName);
                faculty.setLastName(lastName);
                faculty.setMajor(major);
                faculty.setYearsOfExperience(yearsOfExperience);
                faculty.setStudentFeedbackScore(studentFeedbackScore);
                faculty.setIsAvailable(isAvailable);

                return faculty;
            }
        } catch (SQLException e) {
            System.out.println("Faculty Repo - fetchFaculty(): " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Faculty> fetchFaculties() {
        String query = "SELECT * FROM tblfaculties "
                + "INNER JOIN tblusers "
                + "ON tblfaculties.user_id = tblusers.user_id "
                + "WHERE is_archived = 0";

        List<Faculty> faculties = new ArrayList<>();

        try (Connection connnection = dbConnection.connect();
                Statement state = connnection.createStatement();
                ResultSet result = state.executeQuery(query);) {

            while (result.next()) {
                // user
                int id = result.getInt("user_id");
                int facultyId = result.getInt("faculty_id");
                String username = result.getString("username");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String password = result.getString("password");
                int role = result.getInt("role");
                // faculty
                String major = result.getString("major");
                int yearsOfExperience = result.getInt("years_of_experience");
                double studentFeedbackScore = result.getDouble("student_feedback_score");
                int isAvailable = result.getInt("is_available");

                Faculty faculty = new Faculty();

                faculty.setId(id);
                faculty.setFacultyId(facultyId);
                faculty.setUsername(username);
                faculty.setFirstName(firstName);
                faculty.setLastName(lastName);
                faculty.setPassword(password);
                faculty.setRole(role);
                faculty.setMajor(major);
                faculty.setYearsOfExperience(yearsOfExperience);
                faculty.setStudentFeedbackScore(studentFeedbackScore);
                faculty.setIsAvailable(isAvailable);

                faculties.add(faculty);
            }
        } catch (SQLException e) {
            System.out.println("Admin Repo - fetchFaculty(): " + e.getMessage());
        }

        return faculties;
    }

    @Override
    public boolean createFaculty(String username, String password, String firstName, String lastName
    ) {
        // insert to user
        String queryUser = "INSERT INTO tblusers (username, password, first_name, last_name, role) "
                + "VALUES (?,?,?,?,1)";
        String queryFaculty = "INSERT INTO tblfaculties (user_id) VALUES (?)";
        boolean isSuccess = false;

        try (Connection connnection = dbConnection.connect();) {
            try (PreparedStatement prepUser = connnection.prepareStatement(queryUser,
                    Statement.RETURN_GENERATED_KEYS);) {
                prepUser.setString(1, username);
                prepUser.setString(2, password);
                prepUser.setString(3, firstName);
                prepUser.setString(4, lastName);
                prepUser.executeUpdate();

                // get user_id from created user
                ResultSet result = prepUser.getGeneratedKeys();

                if (result.next()) {
                    try (PreparedStatement prepFaculty = connnection.prepareStatement(queryFaculty);) {
                        int userId = result.getInt(1);

                        prepFaculty.setInt(1, userId);
                        isSuccess = prepFaculty.executeUpdate() > 0;
                    } catch (SQLException e) {
                        System.out.println("Admin Repo - createFaculty() - Prep Faculty: " + e.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println("Admin Repo - createFaculty() - Prep User: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Admin Repo - createFaculty() - Connection: " + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public boolean updateUserProfile(int id, String username, String password, String firstName, String lastName) {
        String query = "UPDATE tblusers SET username = ?, password = ?, "
                + "first_name = ?, last_name = ? WHERE user_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setString(1, username);
            prep.setString(2, password);
            prep.setString(3, firstName);
            prep.setString(4, lastName);
            prep.setInt(5, id);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Admin Repo - updateUserProfile(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean updateFacultyProfile(int facultyId, String major,
            int yearsOfExperience, double studentFeedbackScore, int isAvailable
    ) {
        String query = "UPDATE tblfaculties SET major = ?, years_of_experience = ?, "
                + "student_feedback_score = ?, is_available = ? "
                + "WHERE faculty_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setString(1, major);
            prep.setInt(2, yearsOfExperience);
            prep.setDouble(3, studentFeedbackScore);
            prep.setInt(4, isAvailable);
            prep.setInt(5, facultyId);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Admin Repo - updateFacultyProfile(): " + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public boolean archiveFaculty(int id
    ) {
        String query = "UPDATE tblusers SET is_archived = 1 WHERE user_id = ?";
        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query)) {
            prep.setInt(1, id);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Admin Repo - archiveFaculty(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean restoreFaculty(int id
    ) {
        String query = "UPDATE tblusers SET is_archived = 0 WHERE user_id = ?";
        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query)) {
            prep.setInt(1, id);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Admin Repo - restoreFaculty(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean deleteFaculty(int id, int facultyId
    ) {
        String queryUser = "DELETE FROM tblusers WHERE user_id = ?";
        String queryFaculty = "DELETE FROM tblfaculties WHERE faculty_id = ?";
        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();) {
            // Delete faculty
            try (PreparedStatement prepFaculty = connection.prepareStatement(queryFaculty);) {
                prepFaculty.setInt(1, facultyId);
                prepFaculty.executeUpdate();

                // Delete user
                try (PreparedStatement prepUser = connection.prepareStatement(queryUser);) {
                    prepUser.setInt(1, id);
                    prepUser.executeUpdate();
                    isSuccess = true;
                }
            } catch (SQLException e) {
                System.out.println("Admin Repo - deleteFaculty() - Faculty Prep: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Admin Repo - deleteFaculty() - Connection: " + e.getMessage());
        }
        return isSuccess;
    }

}
