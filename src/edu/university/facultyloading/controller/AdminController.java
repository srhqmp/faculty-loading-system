package edu.university.facultyloading.controller;

import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.repo.AdminRepo;

public class AdminController {

    private final AdminRepo adminRepo;

    public AdminController(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Admin login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        return adminRepo.fetchAdmin(username, password);
    }

    public boolean register(String username, String password, String firstName, String lastName) {
        // validate user input
        if (username == null || username.trim().isEmpty()) {
            return false;
        } else if (password == null || password.trim().isEmpty()) {
            return false;
        } else if (firstName == null || firstName.trim().isEmpty()) {
            return false;
        } else if (lastName == null || lastName.trim().isEmpty()) {
            return false;
        } else if (!adminRepo.isUsernameUnique(username)) {
            return false;
        } else {
            return adminRepo.createAdmin(username, password, firstName, lastName);
        }
    }

}
