package edu.university.facultyloading.controller;

import java.util.List;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.repo.FacultyRepo;
import edu.university.facultyloading.util.PromptMessageView;

public class FacultyController {

    private final FacultyRepo facultyRepo;

    public FacultyController(FacultyRepo facultyRepo) {
        this.facultyRepo = facultyRepo;
    }

    public Faculty createFaculty(String username, String password, String firstName, String lastName,
            String major, int yearsOfExperience, double studentFeedbackScore,
            boolean isAvailable) {
        if (!isValid(username, password, firstName, lastName, major, yearsOfExperience, studentFeedbackScore)) {
            return null;
        }

        Faculty faculty = new Faculty();
        faculty.setUsername(username.trim());
        faculty.setPassword(password.trim());
        faculty.setFirstName(firstName.trim());
        faculty.setLastName(lastName.trim());
        faculty.setMajor(major.trim());
        faculty.setYearsOfExperience(yearsOfExperience);
        faculty.setStudentFeedbackScore(studentFeedbackScore);
        faculty.setAvailable(isAvailable);

        return facultyRepo.create(faculty);
    }

    public Faculty authenticate(String username, String password) {
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            return null;
        }
        return facultyRepo.authenticate(username.trim(), password.trim());
    }

    public Faculty getFaculty(int facultyId) {
        if (facultyId <= 0) {
            PromptMessageView.errorMessage("Invalid faculty ID.");
            return null;
        }

        return facultyRepo.getById(facultyId);
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepo.getAll();
    }

    public boolean updateFaculty(int facultyId, int userId, String username, String password, String firstName,
            String lastName, String major, int yearsOfExperience,
            double studentFeedbackScore, boolean isAvailable) {
        if (!isValid(username, password, firstName, lastName, major, yearsOfExperience, studentFeedbackScore)) {
            return false;
        }
        if (facultyId <= 0) {
            PromptMessageView.errorMessage("Invalid faculty ID.");
            return false;
        }

        Faculty faculty = new Faculty(facultyId, userId, username.trim(), password.trim(),
                firstName.trim(), lastName.trim(), major.trim(),
                yearsOfExperience, studentFeedbackScore, isAvailable);
        return facultyRepo.update(faculty);
    }

    public boolean updateAvailability(int facultyId, boolean isAvailable) {
        if (facultyId <= 0) {
            PromptMessageView.errorMessage("Invalid faculty ID.");
            return false;
        }

        return facultyRepo.updateAvailability(facultyId, isAvailable);
    }

    public boolean restoreFaculty(int facultyId) {
        if (facultyId <= 0) {
            PromptMessageView.errorMessage("Invalid faculty ID.");
            return false;
        }

        return facultyRepo.restore(facultyId);
    }

    public boolean deleteFaculty(int facultyId) {
        if (facultyId <= 0) {
            PromptMessageView.errorMessage("Invalid faculty ID.");
            return false;
        }

        return facultyRepo.archive(facultyId);
    }

    private boolean isValid(String username, String password, String firstName, String lastName,
            String major, int yearsOfExperience, double studentFeedbackScore) {
        if (username == null || username.trim().isEmpty())
            return false;
        if (password == null || password.trim().isEmpty())
            return false;
        if (firstName == null || firstName.trim().isEmpty())
            return false;
        if (lastName == null || lastName.trim().isEmpty())
            return false;
        if (major == null || major.trim().isEmpty())
            return false;
        if (yearsOfExperience < 0)
            return false;
        if (studentFeedbackScore < 0)
            return false;
        return true;
    }
}
