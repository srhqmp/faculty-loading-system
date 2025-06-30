package edu.university.facultyloading.controller;

import java.util.List;

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
            System.out.println("A user with username " + username + " already exists.");
            return false;
        } else {
            return facultyRepo.createFaculty(username, password, firstName, lastName);
        }
    }

    // get all faculties
    public boolean updateFaculty(int facultyId, String major, int yearsOfExperience, double studentFeedbackScore,
            int isAvailable) {
        // validate faculty id
        if (facultyId <= 0) {
            System.out.println("Invalid faculty ID.");
            return false;
        }
        // validate fields
        if (major == null || major.trim().isEmpty()) {
            System.out.println("Major cannot be empty.");
            return false;
        }
        if (yearsOfExperience <= 0 || studentFeedbackScore <= 0
                || (isAvailable > 1 && isAvailable < 0)) {
            System.out.println("Invalid input.");
            return false;
        }
        return facultyRepo.updateFacultyProfile(facultyId, major, yearsOfExperience, studentFeedbackScore, isAvailable);
    }

    public boolean deleteFaculty(int id) {
        if (id <= 0) {
            System.out.println("Invalid faculty ID.");
            return false;
        }
        return facultyRepo.archiveFaculty(id);
    }

    public Faculty getFaculty(int id) {
        if (id <= 0) {
            System.out.println("Invalid faculty ID.");
            return null;
        }
        return facultyRepo.fetchFaculty(id);
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepo.fetchFaculties();
    }

}
