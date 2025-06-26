package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Registrar;
import edu.university.facultyloading.repo.RegistrarRepo;
import edu.university.facultyloading.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegistrarRepoImpl implements RegistrarRepo {

    private final DbConnection dbConnection;

    public RegistrarRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Registrar fetchRegistrar(int registrarId) {
        String query = "SELECT * FROM tblregistrars "
                + "INNER JOIN tblusers "
                + "ON tblregistrars.user_id = tblusers.user_id "
                + "WHERE registrar_id = ? "
                + "AND is_archived = 0";

        try (Connection connnection = dbConnection.connect();
                PreparedStatement preparedState = connnection.prepareStatement(query);) {

            preparedState.setInt(1, registrarId);
            ResultSet result = preparedState.executeQuery();

            if (result.next()) {
                Registrar registrar = new Registrar();

                int id = result.getInt("user_id");
                String username = result.getString("username");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");

                registrar.setId(id);
                registrar.setRegistrarId(registrarId);
                registrar.setUsername(username);
                registrar.setFirstName(firstName);
                registrar.setLastName(lastName);

                return registrar;
            }
        } catch (SQLException e) {
            System.out.println("Registrar Repo - fetchRegistrar(): " + e.getMessage());
        }

        return null;
    }

    @Override
    public Registrar fetchRegistrar(String username, String password) {
        String query = "SELECT registrar_id, tblusers.user_id, String first_name, String last_lame "
                + "FROM tblregistrars "
                + "INNER JOIN tblusers "
                + "ON tbladmins.user_id = tblusers.user_id "
                + "WHERE username = ? "
                + "AND password = ? "
                + "AND role = 2 "
                + "AND is_archived = 0";

        try (Connection connnection = dbConnection.connect();
                PreparedStatement preparedState = connnection.prepareStatement(query);) {

            preparedState.setString(1, username);
            preparedState.setString(2, password);
            ResultSet result = preparedState.executeQuery();

            if (result.next()) {
                Registrar registrar = new Registrar();

                int id = result.getInt("user_id");
                int adminId = result.getInt("registrar_id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");

                registrar.setId(id);
                registrar.setRegistrarId(adminId);
                registrar.setUsername(username);
                registrar.setFirstName(firstName);
                registrar.setLastName(lastName);

                return registrar;
            }
        } catch (SQLException e) {
            System.out.println("Registar Repo - fetchRegistrar(): " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Registrar> fetchRegistrars() {
        String query = "SELECT * FROM tblregistrars "
                + "INNER JOIN tblusers "
                + "ON tblregistrars.user_id = tblusers.user_id "
                + "WHERE is_archived = 0";

        List<Registrar> registrars = new ArrayList<>();

        try (Connection connnection = dbConnection.connect();
                Statement state = connnection.createStatement();
                ResultSet result = state.executeQuery(query);) {

            while (result.next()) {
                int id = result.getInt("user_id");
                int registrarId = result.getInt("registrar_id");
                String username = result.getString("username");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");

                Registrar registrar = new Registrar();

                registrar.setId(id);
                registrar.setRegistrarId(registrarId);
                registrar.setUsername(username);
                registrar.setFirstName(firstName);
                registrar.setLastName(lastName);

                registrars.add(registrar);
            }
        } catch (SQLException e) {
            System.out.println("Registrar Repo - fetchRegistrars(): " + e.getMessage());
        }

        return registrars;
    }

    @Override
    public boolean createRegistrar(String username, String password, String firstName, String lastName) {
        // insert to user
        String queryUser = "INSERT INTO tblusers (username, password, first_name, last_name, role) "
                + "VALUES (?,?,?,?,3)";
        String queryRegistrar = "INSERT INTO tblregistrars (user_id) VALUES (?)";
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
                    try (PreparedStatement prepRegistrar = connnection.prepareStatement(queryRegistrar);) {
                        int userId = result.getInt(1);

                        prepRegistrar.setInt(1, userId);
                        isSuccess = prepRegistrar.executeUpdate() > 0;
                    } catch (SQLException e) {
                        System.out.println("Registrar Repo - createRegistrar() - Prep Registrar: " + e.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println("Registrar Repo - createRegistrar() - Prep User: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Registrar Repo - createRegistrar() - Connection: " + e.getMessage());
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
            System.out.println("Registrar Repo - updateUserProfile(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean archiveRegistrar(int id) {
        String query = "UPDATE tblregistrars SET is_archived = 1 WHERE user_id = ?";
        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query)) {
            prep.setInt(1, id);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Registrar Repo - archiveRegistrar(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean restoreRegistrar(int id) {
        String query = "UPDATE tblregistrars SET is_archived = 0 WHERE user_id = ?";
        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query)) {
            prep.setInt(1, id);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Registrar Repo - restoreRegistrar(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean deleteRegistrar(int id, int registrarId) {
        String queryUser = "DELETE FROM tblusers WHERE user_id = ?";
        String queryRegistrar = "DELETE FROM tblregistrars WHERE registrar_id = ?";
        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();) {
            // Delete registrar
            try (PreparedStatement prepRegistrar = connection.prepareStatement(queryRegistrar);) {
                prepRegistrar.setInt(1, registrarId);
                prepRegistrar.executeUpdate();

                // Delete user
                try (PreparedStatement prepUser = connection.prepareStatement(queryUser);) {
                    prepUser.setInt(1, id);
                    prepUser.executeUpdate();
                    isSuccess = true;
                }
            } catch (SQLException e) {
                System.out.println("Registrar Repo - deleteRegistrar() - Registrar Prep: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Registrar Repo - deleteRegistrar() - Connection: " + e.getMessage());
        }
        return isSuccess;
    }

}
