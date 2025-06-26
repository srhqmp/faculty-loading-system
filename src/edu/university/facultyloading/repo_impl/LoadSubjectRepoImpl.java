package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.LoadSubject;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.LoadSubjectRepo;
import edu.university.facultyloading.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoadSubjectRepoImpl implements LoadSubjectRepo {

    private final DbConnection dbConnection;

    public LoadSubjectRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public LoadSubject fetchLoadSubjects(int loadId) {
        String query = "SELECT load_subject_id, load_id, subject_id, subject_name, subject_description "
                + "FROM tblload_subjects "
                + "INNER JOIN tblsubjects "
                + "ON tblload_subjects.subject_id = tblsubjects.subject_id "
                + "WHERE load_id = ? "
                + "AND tblsubjects.is_archived = 0";

        LoadSubject loadSubject = new LoadSubject();
        List<Subject> subjects = new ArrayList<>();

        try (Connection connnection = dbConnection.connect();
                PreparedStatement preparedState = connnection.prepareStatement(query);) {

            preparedState.setInt(1, loadId);
            ResultSet result = preparedState.executeQuery();

            // get loadSubject
            while (result.next()) {
                int id = result.getInt("load_subject_id");
                int subjectId = result.getInt("subject_id");
                String name = result.getString("subject_name");
                String description = result.getString("subject_description");

                if (loadSubject.getId() == 0) {
                    loadSubject.setId(id);
                    loadSubject.setLoadId(loadId);
                }
                // create subject
                Subject subject = new Subject();
                subject.setId(subjectId);
                subject.setName(name);
                subject.setDescription(description);

                subjects.add(subject);
            }
            // set subjects
            loadSubject.setSubjects(subjects);
        } catch (SQLException e) {
            System.out.println("LoadSubject Repo - fetchLoadSubject(): " + e.getMessage());
        }

        return loadSubject;
    }

    @Override
    public boolean addLoadSubject(int loadId, int subjectId) {
        String query = "INSERT INTO tblload_subjects (load_id, subject_id) "
                + "VALUES (?,?)";
        boolean isSuccess = false;

        try (Connection connnection = dbConnection.connect();
                PreparedStatement prep = connnection.prepareStatement(query);) {
            prep.setInt(1, loadId);
            prep.setInt(2, subjectId);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("LoadSubject Repo - addLoadSubject(): " + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public boolean deleteLoadSubjects(int loadId) {
        String query = "DELETE FROM tblload_subjects WHERE load_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setInt(1, loadId);
            prep.executeUpdate();
            isSuccess = true;
        } catch (SQLException e) {
            System.out.println("LoadSubject Repo - deleteLoadSubject(): " + e.getMessage());
        }
        return isSuccess;
    }

}
