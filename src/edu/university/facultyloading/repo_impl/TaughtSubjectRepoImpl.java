package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.TaughtSubject;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.model.TaughtSubject;
import edu.university.facultyloading.repo.TaughtSubjectRepo;
import edu.university.facultyloading.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaughtSubjectRepoImpl implements TaughtSubjectRepo {

    private final DbConnection dbConnection;

    public TaughtSubjectRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public TaughtSubject fetchTaughtSubjects(int facultyId) {
        String query = "SELECT taught_subject_id, faculty_id, subject_id, subject_name, subject_description "
                + "FROM tbltaught_subjects "
                + "INNER JOIN tblsubjects "
                + "ON tbltaught_subjects.subject_id = tblsubjects.subject_id "
                + "WHERE faculty_id = ? "
                + "AND tblsubjects.is_archived = 0";

        TaughtSubject taughtSubject = new TaughtSubject();
        List<Subject> subjects = new ArrayList<>();

        try (Connection connnection = dbConnection.connect();
                PreparedStatement preparedState = connnection.prepareStatement(query);) {

            preparedState.setInt(1, facultyId);
            ResultSet result = preparedState.executeQuery();

            // get taught Subject
            while (result.next()) {
                int id = result.getInt("taught_subject_id");
                int subjectId = result.getInt("subject_id");
                String name = result.getString("subject_name");
                String description = result.getString("subject_description");

                if (taughtSubject.getId() == 0) {
                    taughtSubject.setId(id);
                    taughtSubject.setFacultyId(facultyId);
                }
                // create subject
                Subject subject = new Subject();
                subject.setId(subjectId);
                subject.setName(name);
                subject.setDescription(description);

                subjects.add(subject);
            }
            // set subjects
            taughtSubject.setSubjects(subjects);
        } catch (SQLException e) {
            System.out.println("TaughtSubject Repo - fetchTaughtSubjects(): " + e.getMessage());
        }

        return taughtSubject;
    }

    @Override
    public boolean addTaughtSubject(int facultyId, int subjectId) {
        String query = "INSERT INTO tbltaught_subjects (faculty_id, subject_id) "
                + "VALUES (?,?)";
        boolean isSuccess = false;

        try (Connection connnection = dbConnection.connect();
                PreparedStatement prep = connnection.prepareStatement(query);) {
            prep.setInt(1, facultyId);
            prep.setInt(2, subjectId);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("TaughtSubject Repo - addTaughtSubject(): " + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public boolean deleteTaughtSubject(int id) {
        String query = "DELETE FROM tbltaught_subjects WHERE taught_subject_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setInt(1, id);
            prep.executeUpdate();
            isSuccess = true;
        } catch (SQLException e) {
            System.out.println("TaughtSubject Repo - deleteTaughtSubject(): " + e.getMessage());
        }
        return isSuccess;
    }

}
