package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Load;
import edu.university.facultyloading.repo.LoadRepo;
import edu.university.facultyloading.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoadRepoImpl implements LoadRepo {

    private final DbConnection dbConnection;

    public LoadRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Load fetchLoad(int facultyId) {
        String query = "SELECT * FROM tblloads WHERE faculty_id = ? AND is_archived = 0";

        Load load = new Load();

        try (Connection connnection = dbConnection.connect();
                PreparedStatement preparedState = connnection.prepareStatement(query);) {

            preparedState.setInt(1, facultyId);
            ResultSet result = preparedState.executeQuery();

            if (result.next()) {
                int id = result.getInt("load_id");

                load.setId(id);
                load.setFacultyId(facultyId);
            }
        } catch (SQLException e) {
            System.out.println("Load Repo - fetchLoad(): " + e.getMessage());
        }

        return load;
    }

    @Override
    public List<Load> fetchLoads() {
        String query = "SELECT * FROM tblloads WHERE is_archived = 0";

        List<Load> loads = new ArrayList<>();

        try (Connection connnection = dbConnection.connect();
                Statement state = connnection.createStatement();
                ResultSet result = state.executeQuery(query);) {

            while (result.next()) {
                int id = result.getInt("load_id");
                int facultyId = result.getInt("faculty_id");

                Load load = new Load();

                load.setId(id);
                load.setFacultyId(facultyId);
                loads.add(load);
            }
        } catch (SQLException e) {
            System.out.println("Load Repo - fetchLoads(): " + e.getMessage());
        }

        return loads;
    }

    @Override
    public boolean createLoad(int facultyId) {
        String query = "INSERT INTO tblloads (faculty_id) VALUES (?)";
        boolean isSuccess = false;

        try (Connection connnection = dbConnection.connect();
                PreparedStatement prep = connnection.prepareStatement(query);) {
            prep.setInt(1, facultyId);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Load Repo - createLoad(): " + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public boolean archiveLoad(int id) {
        String query = "UPDATE tblloads SET is_archived = 1 WHERE load_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setInt(1, id);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Load Repo - archiveLoad(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean restoreLoad(int id) {
        String query = "UPDATE tblloads SET is_archived = 0 WHERE load_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query);) {
            prep.setInt(1, id);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Load Repo - restoreLoad(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean deleteLoad(int id) {
        String queryLoad = "DELETE FROM tblloads WHERE load_id = ?";
        String queryLoadSubjects = "DELETE FROM tblload_subjects WHERE load_id = ?";

        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();) {
            // delete load
            try (PreparedStatement prepLoad = connection.prepareStatement(queryLoad);) {
                prepLoad.setInt(1, id);
                boolean isLoadDeleted = prepLoad.executeUpdate() > 0;

                if (isLoadDeleted) {
                    // delete load subjects
                    try (PreparedStatement prepLoadSubjects = connection.prepareStatement(queryLoadSubjects);) {
                        prepLoadSubjects.setInt(1, id);
                        prepLoadSubjects.executeUpdate();
                        isSuccess = true;
                    } catch (SQLException e) {
                        System.out.println("Load Repo - deleteLoad() - Prep Load Subjects: " + e.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println("Load Repo - deleteLoad() - Prep Load: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Load Repo - deleteLoad() - Connection: " + e.getMessage());
        }
        return isSuccess;
    }

}
