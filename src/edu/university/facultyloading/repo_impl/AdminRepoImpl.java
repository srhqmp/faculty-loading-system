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

    public Admin create(Admin admin) {
        String queryUser = "INSERT INTO tblusers (username, password, first_name, last_name, role) VALUES (?, ?, ?, ?, 2)";
        String queryAdmin = "INSERT INTO tbladmins (user_id) VALUES (?)";

        Admin createdAdmin = null;

        try (Connection connection = dbConnection.connect()) {
            try (PreparedStatement prepUser = connection.prepareStatement(queryUser, Statement.RETURN_GENERATED_KEYS)) {
                prepUser.setString(1, admin.getUsername());
                prepUser.setString(2, admin.getPassword());
                prepUser.setString(3, admin.getFirstName());
                prepUser.setString(4, admin.getLastName());
                prepUser.executeUpdate();

                ResultSet userKeys = prepUser.getGeneratedKeys();
                if (userKeys.next()) {
                    int userId = userKeys.getInt(1);
                    admin.setUserId(userId);

                    try (PreparedStatement prepAdmin = connection.prepareStatement(queryAdmin,
                            Statement.RETURN_GENERATED_KEYS)) {
                        prepAdmin.setInt(1, userId);
                        int rowsAffected = prepAdmin.executeUpdate();

                        if (rowsAffected > 0) {
                            ResultSet adminKeys = prepAdmin.getGeneratedKeys();
                            int adminId = -1;
                            if (adminKeys.next()) {
                                adminId = adminKeys.getInt(1);
                            }
                            admin.setAdminId(adminId);

                            // Return the updated admin object with IDs set
                            createdAdmin = admin;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("AdminRepoImpl - createAdmin(): " + e.getMessage());
        }

        return createdAdmin;
    }

    @Override
    public Admin authenticate(String username, String password) {
        String query = "SELECT tbladmins.admin_id, tblusers.* " +
                "FROM tbladmins " +
                "INNER JOIN tblusers ON tbladmins.user_id = tblusers.user_id " +
                "WHERE tblusers.username = ? " +
                "AND tblusers.password = ? " +
                "AND tblusers.role = 2 " +
                "AND tblusers.is_archived = 0";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new Admin(
                        result.getInt("admin_id"),
                        result.getInt("user_id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("first_name"),
                        result.getString("last_name"));
            }

        } catch (SQLException e) {
            System.out.println("AdminRepoImpl - authenticate(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public Admin getById(int adminId) {
        String query = "SELECT tbladmins.admin_id, tblusers.* " +
                "FROM tbladmins " +
                "INNER JOIN tblusers ON tbladmins.user_id = tblusers.user_id " +
                "WHERE tbladmins.admin_id = ? " +
                "AND tblusers.is_archived = 0";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, adminId);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new Admin(
                        result.getInt("admin_id"),
                        result.getInt("user_id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("first_name"),
                        result.getString("last_name"));
            }

        } catch (SQLException e) {
            System.out.println("AdminRepoImpl - getById(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT tbladmins.admin_id, tblusers.* " +
                "FROM tbladmins " +
                "INNER JOIN tblusers ON tbladmins.user_id = tblusers.user_id " +
                "WHERE tblusers.is_archived = 0";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                Admin admin = new Admin(
                        result.getInt("admin_id"),
                        result.getInt("user_id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("first_name"),
                        result.getString("last_name"));
                admins.add(admin);
            }

        } catch (SQLException e) {
            System.out.println("AdminRepoImpl - getAll(): " + e.getMessage());
        }
        return admins;
    }

    @Override
    public boolean update(Admin admin) {
        String query = "UPDATE tblusers SET username = ?, password = ?, first_name = ?, last_name = ? WHERE user_id = ?";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            stmt.setString(3, admin.getFirstName());
            stmt.setString(4, admin.getLastName());
            stmt.setInt(5, admin.getUserId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("AdminRepoImpl - update(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean archive(int adminId) {
        String query = "UPDATE tblusers SET is_archived = 1 WHERE user_id = (SELECT user_id FROM tbladmins WHERE admin_id = ?)";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, adminId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("AdminRepoImpl - archive(): " + e.getMessage());

        }
        return false;
    }

    @Override
    public boolean restore(int adminId) {
        String query = "UPDATE tblusers SET is_archived = 0 WHERE user_id = (SELECT user_id FROM tbladmins WHERE admin_id = ?)";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, adminId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("AdminRepoImpl - restore(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int adminId) {
        String query = "DELETE FROM tblusers WHERE user_id = (SELECT user_id FROM tbladmins WHERE admin_id = ?)";

        try (Connection connection = dbConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, adminId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("AdminRepoImpl - delete(): " + e.getMessage());
        }
        return false;
    }
}
