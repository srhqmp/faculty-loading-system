package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Training;
import edu.university.facultyloading.model.Training;
import edu.university.facultyloading.model.Training;
import edu.university.facultyloading.repo.TrainingRepo;
import edu.university.facultyloading.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrainingRepoImpl implements TrainingRepo {

    private final DbConnection dbConnection;

    public TrainingRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Training fetchTraining(int id) {
        String query = "SELECT * FROM tbltrainings WHERE training_id = ?";

        Training training = new Training();

        try (Connection connnection = dbConnection.connect();
                PreparedStatement preparedState = connnection.prepareStatement(query);) {

            preparedState.setInt(1, id);
            ResultSet result = preparedState.executeQuery();

            if (result.next()) {
                String title = result.getString("training_title");
                String description = result.getString("training_description");

                training.setId(id);
                training.setTitle(title);
                training.setDescription(description);
            }
        } catch (SQLException e) {
            System.out.println("Training Repo - fetchTraining(): " + e.getMessage());
        }

        return training;
    }

    @Override
    public List<Training> fetchTrainings() {
        String query = "SELECT * FROM tbltrainings";

        List<Training> trainings = new ArrayList<>();

        try (Connection connnection = dbConnection.connect();
                Statement state = connnection.createStatement();
                ResultSet result = state.executeQuery(query);) {

            while (result.next()) {
                int id = result.getInt("training_id");
                String title = result.getString("training_title");
                String description = result.getString("training_description");

                Training training = new Training();

                training.setId(id);
                training.setTitle(title);
                training.setDescription(description);

                trainings.add(training);
            }
        } catch (SQLException e) {
            System.out.println("Training Repo - fetchTrainings(): " + e.getMessage());
        }

        return trainings;
    }

    @Override
    public boolean createTraining(String title, String description) {
        String query = "INSERT INTO tbltrainings (training_title, training_description) "
                + "VALUES (?,?)";
        boolean isSuccess = false;

        try (Connection connnection = dbConnection.connect();
                PreparedStatement prep = connnection.prepareStatement(query);) {
            prep.setString(1, title);
            prep.setString(2, description);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Training Repo - createTraining() - Prep Training: " + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public boolean updateTraining(int id, String title, String description) {
        String query = "UPDATE tbltrainings SET training_title = ?, training_description = ? WHERE training_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setString(1, title);
            prep.setString(2, description);
            prep.setInt(3, id);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Training Repo - updateTraining(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean deleteTraining(int id) {
        String query = "DELETE FROM tbltrainings WHERE training_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setInt(1, id);
            prep.executeUpdate();
            isSuccess = true;
        } catch (SQLException e) {
            System.out.println("Training Repo - deleteTraining(): " + e.getMessage());
        }
        return isSuccess;
    }

}
