package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.repo.AdminRepo;
import edu.university.facultyloading.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminRepoImpl implements AdminRepo {

    private final DbConnection dbConnection;

    public AdminRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Admin fetchAdmin(int adminId) {
        String query = "SELECT admin_id, tbladmins.user_id AS user_id, username, first_name, last_name "
                + "FROM tbladmins "
                + "INNER JOIN tblusers "
                + "ON tbladmins.user_id = tblusers.user_id "
                + "WHERE admin_id = ? "
                + "AND is_archived = 0";

        try (Connection connection = dbConnection.connect();
                PreparedStatement preparedState = connection.prepareStatement(query)) {

            preparedState.setInt(1, adminId);
            ResultSet result = preparedState.executeQuery();

            if (result.next()) {
                Admin admin = new Admin();

                int id = result.getInt("user_id");
                String username = result.getString("username");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");

                admin.setId(id);
                admin.setAdminId(adminId);
                admin.setUsername(username);
                admin.setFirstName(firstName);
                admin.setLastName(lastName);

                return admin;
            }
        } catch (SQLException e) {
            System.out.println("Admin Repo - fetchAdmin(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public Admin fetchAdmin(String username, String password) {
        String query = "SELECT admin_id, tblusers.user_id, String first_name, String last_name "
                + "FROM tbladmins "
                + "INNER JOIN tblusers "
                + "ON tbladmins.user_id = tblusers.user_id "
                + "WHERE username = ? "
                + "AND password = ? "
                + "AND role = 3 "
                + "AND is_archived = 0";

        try (Connection connnection = dbConnection.connect();
                PreparedStatement preparedState = connnection.prepareStatement(query);) {

            preparedState.setString(1, username);
            preparedState.setString(2, password);
            ResultSet result = preparedState.executeQuery();

            if (result.next()) {
                Admin admin = new Admin();

                int id = result.getInt("user_id");
                int adminId = result.getInt("admin_id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");

                admin.setId(id);
                admin.setAdminId(adminId);
                admin.setUsername(username);
                admin.setFirstName(firstName);
                admin.setLastName(lastName);

                return admin;
            }
        } catch (SQLException e) {
            System.out.println("Admin Repo - fetchAdmin(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Admin> fetchAdmins() {
        String query = "SELECT admin_id, tbladmins.user_id AS user_id, username, first_name, last_name "
                + "FROM tbladmins "
                + "INNER JOIN tblusers "
                + "ON tbladmins.user_id = tblusers.user_id "
                + "WHERE is_archived = 0";

        List<Admin> admins = new ArrayList<>();

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                Admin admin = new Admin();

                admin.setAdminId(result.getInt("admin_id"));
                admin.setId(result.getInt("user_id"));
                admin.setUsername(result.getString("username"));
                admin.setFirstName(result.getString("first_name"));
                admin.setLastName(result.getString("last_name"));

                admins.add(admin);
            }

        } catch (SQLException e) {
            System.out.println("Admin Repo - fetchAdmins(): " + e.getMessage());
        }

        return admins;
    }

    @Override
    public boolean createAdmin(String username, String password, String firstName, String lastName) {
        // insert to user
        String queryUser = "INSERT INTO tblusers (username, password, first_name, last_name, role) VALUES (?, ?, ?, ?, 3)";
        String queryAdmin = "INSERT INTO tbladmins (user_id) VALUES (?)";
        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();) {
            try (PreparedStatement prepUser = connection.prepareStatement(queryUser,
                    Statement.RETURN_GENERATED_KEYS);) {
                prepUser.setString(1, username);
                prepUser.setString(2, password);
                prepUser.setString(3, firstName);
                prepUser.setString(4, lastName);
                prepUser.executeUpdate();

                // get user_id from created user
                ResultSet result = prepUser.getGeneratedKeys();

                if (result.next()) {
                    try (PreparedStatement prepAdmin = connection.prepareStatement(queryAdmin);) {
                        int userId = result.getInt(1);

                        prepAdmin.setInt(1, userId);
                        isSuccess = prepAdmin.executeUpdate() > 0;
                    } catch (SQLException e) {
                        System.out.println("Admin Repo - createAdmin() - Prep Admin: " + e.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println("Admin Repo - createAdmin() - Prep User: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Admin Repo - createAdmin() - Connection: " + e.getMessage());
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
    public boolean archiveAdmin(int id) {
        String query = "UPDATE tbladmins SET is_archived = 1 WHERE user_id = ?";
        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query)) {
            prep.setInt(1, id);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Admin Repo - archiveAdmin(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean restoreAdmin(int id) {
        String query = "UPDATE tbladmins SET is_archived = 0 WHERE user_id = ?";
        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();
                PreparedStatement prep = connection.prepareStatement(query)) {
            prep.setInt(1, id);

            isSuccess = prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Admin Repo - restoreAdmin(): " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean deleteAdmin(int id, int adminId) {
        String queryUser = "DELETE FROM tblusers WHERE user_id = ?";
        String queryAdmin = "DELETE FROM tbladmins WHERE admin_id = ?";
        boolean isSuccess = false;

        try (Connection connection = dbConnection.connect();) {
            // Delete admin
            try (PreparedStatement prepAdmin = connection.prepareStatement(queryAdmin);) {
                prepAdmin.setInt(1, adminId);
                prepAdmin.executeUpdate();

                // Delete user
                try (PreparedStatement prepUser = connection.prepareStatement(queryUser);) {
                    prepUser.setInt(1, id);
                    prepUser.executeUpdate();
                    isSuccess = true;
                }
            } catch (SQLException e) {
                System.out.println("Admin Repo - deleteAdmin() - Admin Prep: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Admin Repo - deleteAdmin() - Connection: " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean isUsernameUnique(String username) {
        String query = "SELECT COUNT(*) FROM tblusers WHERE username = ?";

        try (Connection connection = dbConnection.connect();
                Statement state = connection.createStatement()) {

            ResultSet result = state.executeQuery(query);
            if (result.next()) {
                int count = result.getInt(1);
                return count == 0; // true if unique
            }

        } catch (SQLException e) {
            System.out.println("Admin Repo - isUsernameUnique(): " + e.getMessage());
        }
        return false;
    }

}
