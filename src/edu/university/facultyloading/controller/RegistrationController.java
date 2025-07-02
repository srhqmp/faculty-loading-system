package edu.university.facultyloading.controller;

import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.util.PromptMessageView;

public class RegistrationController {
    private final AdminController adminController;
    private final FacultyController facultyController;

    public RegistrationController(AdminController adminController, FacultyController facultyController) {
        this.adminController = adminController;
        this.facultyController = facultyController;
    }

    public boolean registerUser(String[] data) {
        int userType = Integer.parseInt(data[0]);

        String username = data[1].trim();
        String password = data[2].trim();
        String firstName = data[3].trim();
        String lastName = data[4].trim();

        if (userType == 1) {
            return registerAdmin(username, password, firstName, lastName);
        } else {
            return registerFaculty(data, username, password, firstName, lastName);
        }
    }

    private boolean registerAdmin(String username, String password, String firstName, String lastName) {
        try {
            Admin admin = adminController.createAdmin(username, password, firstName, lastName);
            if (admin == null) {
                throw new Exception();
            }
            PromptMessageView.successMessage("Admin registered successfully.");
            return true;
        } catch (Exception e) {
            PromptMessageView
                    .errorMessage("Registration failed. Please check your input.");
            return false;
        }
    }

    private boolean registerFaculty(String[] data, String username, String password, String firstName,
            String lastName) {

        try {
            String major = data[5].trim();
            int yearsOfExperience = Integer.parseInt(data[6].trim());

            Faculty faculty = facultyController.createFaculty(
                    username, password, firstName, lastName,
                    major, yearsOfExperience, 0.0, true);
            if (faculty == null) {
                throw new Exception();
            }
            PromptMessageView.successMessage("Faculty registered successfully.");
            return true;
        } catch (Exception e) {
            PromptMessageView
                    .errorMessage("Registration failed. Please check your input.");
            return false;
        }
    }
}
