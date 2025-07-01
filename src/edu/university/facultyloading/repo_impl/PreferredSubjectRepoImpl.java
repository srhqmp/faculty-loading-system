package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.PreferredSubject;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.PreferredSubjectRepo;
import edu.university.facultyloading.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreferredSubjectRepoImpl implements PreferredSubjectRepo {

    private final DbConnection dbConnection;

    public PreferredSubjectRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public PreferredSubject fetchPreferredSubjects(int facultyId) {
        String query = "SELECT preferred_subject_id, faculty_id, subject_id, subject_name, subject_description "
                + "FROM tblpreferred_subjects "
                + "INNER JOIN tblsubjects "
                + "ON tblpreferred_subjects.subject_id = tblsubjects.subject_id "
                + "WHERE faculty_id = ? "
                + "AND tblsubjects.is_archived = 0";

        PreferredSubject preferredSubject = new PreferredSubject();
        List<Subject> subjects = new ArrayList<>();

        try (Connection connnection = dbConnection.connect();
                PreparedStatement preparedState = connnection.prepareStatement(query);) {

            preparedState.setInt(1, facultyId);
            ResultSet result = preparedState.executeQuery();

            // get preferred Subject
            while (result.next()) {
                int id = result.getInt("preferred_subject_id");
                int subjectId = result.getInt("subject_id");
                String name = result.getString("subject_name");
                String description = result.getString("subject_description");

                if (preferredSubject.getId() == 0) {
                    preferredSubject.setId(id);
                    preferredSubject.setFacultyId(facultyId);
                }
                // create subject
                Subject subject = new Subject();
                subject.setId(subjectId);
                subject.setName(name);
                subject.setDescription(description);

                subjects.add(subject);
            }
            // set subjects
            preferredSubject.setSubjects(subjects);
        } catch (SQLException e) {
            System.out.println("PreferredSubject Repo - fetchPreferredSubjects(): " + e.getMessage());
        }

        return preferredSubject;
    }

    @Override
    public boolean addPreferredSubject(int facultyId, int subjectId) {
        String query = "INSERT INTO tblpreferred_subjects (faculty_id, subject_id) "
                + "VALUES (?,?)";
        boolean isSuccess = false;

        try (Connection connnection = dbConnection.connect();
                PreparedStatement prep = connnection.prepareStatement(query);) {
            prep.setInt(1, facultyId);
            prep.setInt(2, subjectId);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("PreferredSubject Repo - addPreferredSubject(): " + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public boolean deletePreferredSubject(int id) {
        String query = "DELETE FROM tblpreferred_subjects WHERE preferred_subject_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setInt(1, id);
            prep.executeUpdate();
            isSuccess = true;
        } catch (SQLException e) {
            System.out.println("PreferredSubject Repo - deletePreferredSubject(): " + e.getMessage());
        }
        return isSuccess;
    }

}
