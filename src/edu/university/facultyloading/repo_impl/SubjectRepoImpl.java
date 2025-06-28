package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.SubjectRepo;
import edu.university.facultyloading.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepoImpl implements SubjectRepo {

    private final DbConnection dbConnection;

    public SubjectRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Subject fetchSubject(int id) {
        String query = "SELECT * FROM tblsubjects WHERE subject_id = ? AND is_archived = 0";

        try (Connection connnection = dbConnection.connect();
                PreparedStatement preparedState = connnection.prepareStatement(query);) {

            preparedState.setInt(1, id);
            ResultSet result = preparedState.executeQuery();

            if (result.next()) {
                Subject subject = new Subject();
                String name = result.getString("subject_name");
                String description = result.getString("subject_description");

                subject.setId(id);
                subject.setName(name);
                subject.setDescription(description);

                return subject;
            }
        } catch (SQLException e) {
            System.out.println("Subject Repo - fetchSubject(): " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Subject> fetchSubjects() {
        String query = "SELECT * FROM tblsubjects WHERE is_archived = 0";

        List<Subject> subjects = new ArrayList<>();

        try (Connection connnection = dbConnection.connect();
                Statement state = connnection.createStatement();
                ResultSet result = state.executeQuery(query);) {

            while (result.next()) {
                int id = result.getInt("subject_id");
                String name = result.getString("subject_name");
                String description = result.getString("subject_description");

                Subject subject = new Subject();

                subject.setId(id);
                subject.setName(name);
                subject.setDescription(description);

                subjects.add(subject);
            }
        } catch (SQLException e) {
            System.out.println("Subject Repo - fetchSubjects(): " + e.getMessage());
        }

        return subjects;
    }

    @Override
    public boolean createSubject(String name, String description) {
        String query = "INSERT INTO tblsubjects (subject_name, subject_description) "
                + "VALUES (?,?)";
        boolean isSuccess = false;

        try (Connection connnection = dbConnection.connect();
                PreparedStatement prep = connnection.prepareStatement(query);) {
            prep.setString(1, name);
            prep.setString(2, description);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Subject Repo - createSubject(): " + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public boolean updateSubject(int id, String name, String description) {
        String query = "UPDATE tblsubjects SET subject_name = ?, subject_description = ? WHERE subject_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setString(1, name);
            prep.setString(2, description);
            prep.setInt(3, id);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Subject Repo - updateSubject(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean archiveSubject(int id) {
        String query = "UPDATE tblsubjects SET is_archived = 1 WHERE subject_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setInt(1, id);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Subject Repo - archiveSubject(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean restoreSubject(int id) {
        String query = "UPDATE tblsubjects SET is_archived = 0 WHERE subject_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setInt(1, id);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Subject Repo - restoreSubject(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean deleteSubject(int id) {
        String query = "DELETE FROM tblsubjects WHERE subject_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setInt(1, id);
            prep.executeUpdate();
            isSuccess = true;
        } catch (SQLException e) {
            System.out.println("Subject Repo - deleteSubject(): " + e.getMessage());
        }
        return isSuccess;
    }

}
