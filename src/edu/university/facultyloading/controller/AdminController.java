package edu.university.facultyloading.controller;

import java.util.List;
import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.repo.AdminRepo;

public class AdminController {

    private final AdminRepo adminRepo;

    public AdminController(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Admin createAdmin(String username, String password, String firstName, String lastName) {
        if (!isValid(username, password, firstName, lastName)) {
            return null;
        }

        Admin admin = new Admin();
        admin.setUsername(username.trim());
        admin.setPassword(password.trim());
        admin.setFirstName(firstName.trim());
        admin.setLastName(lastName.trim());

        return adminRepo.create(admin);
    }

    public Admin login(String username, String password) {
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            return null;
        }

        return adminRepo.authenticate(username.trim(), password.trim());
    }

    public Admin getAdmin(int adminId) {
        return adminRepo.getById(adminId);
    }

    public List<Admin> getAllAdmins() {
        return adminRepo.getAll();
    }

    public boolean updateAdmin(int adminId, int userId, String username, String password, String firstName,
            String lastName) {
        if (!isValid(username, password, firstName, lastName)) {
            return false;
        }

        Admin admin = new Admin(adminId, userId, username.trim(), password.trim(), firstName.trim(), lastName.trim());
        return adminRepo.update(admin);
    }

    public boolean archiveAdmin(int adminId) {
        return adminRepo.archive(adminId);
    }

    public boolean restoreAdmin(int adminId) {
        return adminRepo.restore(adminId);
    }

    public boolean deleteAdmin(int adminId) {
        return adminRepo.delete(adminId);
    }

    private boolean isValid(String username, String password, String firstName, String lastName) {
        return username != null && !username.trim().isEmpty() &&
                password != null && !password.trim().isEmpty() &&
                firstName != null && !firstName.trim().isEmpty() &&
                lastName != null && !lastName.trim().isEmpty();
    }
}
