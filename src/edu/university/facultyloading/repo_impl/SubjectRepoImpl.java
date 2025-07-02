package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.SubjectRepo;
import edu.university.facultyloading.util.DbConnection;
import edu.university.facultyloading.util.PromptMessageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepoImpl implements SubjectRepo {

    private final DbConnection dbConnection;

    public SubjectRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean create(Subject subject) {
        String query = "INSERT INTO tblsubjects (name, description, recommended_major, complexity_level) VALUES (?, ?, ?, ?)";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, subject.getName());
            stmt.setString(2, subject.getDescription());
            stmt.setString(3, subject.getRecommendedMajor());
            stmt.setInt(4, subject.getComplexityLevel());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            PromptMessageView.errorMessage("SubjectRepo - create(): " + e.getMessage());
            return false;
        }
    }

    @Override
    public Subject getById(int subjectId) {
        String query = "SELECT subject_id, name, description, recommended_major, complexity_level FROM tblsubjects WHERE subject_id = ? AND is_archived = 0";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, subjectId);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new Subject(
                        result.getInt("subject_id"),
                        result.getString("name"),
                        result.getString("description"),
                        result.getString("recommended_major"),
                        result.getInt("complexity_level"));
            }
        } catch (SQLException e) {
            PromptMessageView.errorMessage("SubjectRepo - getById(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Subject> getAll() {
        List<Subject> subjects = new ArrayList<>();
        String query = "SELECT subject_id, name, description, recommended_major, complexity_level FROM tblsubjects WHERE is_archived = 0";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                subjects.add(new Subject(
                        result.getInt("subject_id"),
                        result.getString("name"),
                        result.getString("description"),
                        result.getString("recommended_major"),
                        result.getInt("complexity_level")));
            }
        } catch (SQLException e) {
            PromptMessageView.errorMessage("SubjectRepo - getAll(): " + e.getMessage());
        }
        return subjects;
    }

    @Override
    public boolean update(Subject subject) {
        String query = "UPDATE tblsubjects SET name = ?, description = ?, recommended_major = ?, complexity_level = ? WHERE subject_id = ?";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, subject.getName());
            stmt.setString(2, subject.getDescription());
            stmt.setString(3, subject.getRecommendedMajor());
            stmt.setInt(4, subject.getComplexityLevel());
            stmt.setInt(5, subject.getSubjectId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            PromptMessageView.errorMessage("SubjectRepo - update(): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean archive(int subjectId) {
        String query = "UPDATE tblsubjects SET is_archived = 1 WHERE subject_id = ?";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, subjectId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            PromptMessageView.errorMessage("SubjectRepo - archive(): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean restore(int subjectId) {
        String query = "UPDATE tblsubjects SET is_archived = 0 WHERE subject_id = ?";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, subjectId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            PromptMessageView.errorMessage("SubjectRepo - restore(): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int subjectId) {
        String query = "DELETE FROM tblsubjects WHERE subject_id = ?";
        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, subjectId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            PromptMessageView.errorMessage("SubjectRepo - delete(): " + e.getMessage());
            return false;
        }
    }
}
