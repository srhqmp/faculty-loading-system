package edu.university.facultyloading.controller;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.repo.FacultyRepo;

public class FacultyController {

    private final FacultyRepo facultyRepo;

    public FacultyController(FacultyRepo facultyRepo) {
        this.facultyRepo = facultyRepo;
    }

    public Faculty login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        return facultyRepo.fetchFaculty(username, password);
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
        } else if (!facultyRepo.isUsernameUnique(username)) {
            System.out.println("A user with username " + username + "already exists.");
            return false;
        } else {
            return facultyRepo.createFaculty(username, password, firstName, lastName);
        }
    }

}
