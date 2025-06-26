package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.FacultyTraining;
import edu.university.facultyloading.model.Training;
import edu.university.facultyloading.repo.FacultyTrainingRepo;
import edu.university.facultyloading.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyTrainingRepoImpl implements FacultyTrainingRepo {

    private final DbConnection dbConnection;

    public FacultyTrainingRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public FacultyTraining fetchFacultyTrainings(int facultyId) {
        String query = "SELECT faculty_training_id, faculty_id, role, training_id, training_title, description "
                + "FROM tblfaculty_trainings "
                + "INNER JOIN tbltrainings "
                + "ON tblfaculty_trainings.training_id = tbltrainings.training_id "
                + "WHERE faculty_id = ? ";

        FacultyTraining facultyTraining = new FacultyTraining();
        List<Training> trainings = new ArrayList<>();

        try (Connection connnection = dbConnection.connect();
                PreparedStatement preparedState = connnection.prepareStatement(query);) {

            preparedState.setInt(1, facultyId);
            ResultSet result = preparedState.executeQuery();

            // get faculty Training
            while (result.next()) {
                int id = result.getInt("faculty_training_id");
                int trainingId = result.getInt("training_id");
                String title = result.getString("training_title");
                String description = result.getString("description");
                String role = result.getString("role");

                if (facultyTraining.getId() == 0) {
                    facultyTraining.setId(id);
                    facultyTraining.setFacultyId(facultyId);
                    facultyTraining.setRole(role);
                }
                // create training
                Training training = new Training();
                training.setId(trainingId);
                training.setTitle(title);
                training.setDescription(description);

                trainings.add(training);
            }
            // set trainings
            facultyTraining.setTrainings(trainings);
        } catch (SQLException e) {
            System.out.println("FacultyTraining Repo - fetchFacultyTrainings(): " + e.getMessage());
        }

        return facultyTraining;
    }

    @Override
    public boolean addFacultyTraining(int facultyId, String role, int trainingId) {
        String query = "INSERT INTO tblfaculty_trainings (faculty_id, role, training_id) "
                + "VALUES (?,?,?)";
        boolean isSuccess = false;

        try (Connection connnection = dbConnection.connect();
                PreparedStatement prep = connnection.prepareStatement(query);) {
            prep.setInt(1, facultyId);
            prep.setString(2, role);
            prep.setInt(3, trainingId);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("FacultyTraining Repo - addFacultyTraining(): " + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public boolean updateFacultyTraining(int id, String role) {
        String query = "UPDATE tblfaculty_trainings SET role = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setString(1, role);
            prep.executeUpdate();
            isSuccess = true;
        } catch (SQLException e) {
            System.out.println("FacultyTraining Repo - updateFacultyTraining(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean deleteFacultyTraining(int id) {
        String query = "DELETE FROM tblfaculty_trainings WHERE faculty_training_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setInt(1, id);
            prep.executeUpdate();
            isSuccess = true;
        } catch (SQLException e) {
            System.out.println("FacultyTraining Repo - deleteFacultyTraining(): " + e.getMessage());
        }
        return isSuccess;
    }

}
