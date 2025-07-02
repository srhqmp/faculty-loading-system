package edu.university.facultyloading.controller;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Load;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.LoadRepo;
import edu.university.facultyloading.util.FacultyFilter;
import edu.university.facultyloading.util.PromptMessageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadController {
    private final LoadRepo loadRepo;

    public LoadController(LoadRepo loadRepo) {
        this.loadRepo = loadRepo;
    }

    public boolean createLoad(int facultyId, int subjectId) {
        if (facultyId <= 0 || subjectId <= 0) {
            PromptMessageView.errorMessage("Invalid faculty or subject ID.");
            return false;
        }

        Load load = new Load(0, facultyId, subjectId);
        return loadRepo.create(load);
    }

    public Load getLoadById(int loadId) {
        if (loadId <= 0) {
            PromptMessageView.errorMessage("Invalid load ID.");
            return null;
        }

        return loadRepo.getById(loadId);
    }

    public List<Load> getAllLoads() {
        return loadRepo.getAll();
    }

    public List<Load> getLoadsByFacultyId(int facultyId) {
        if (facultyId <= 0) {
            PromptMessageView.errorMessage("Invalid faculty ID.");
            return null;
        }

        return loadRepo.getByFacultyId(facultyId);
    }

    public boolean updateLoad(int loadId, int facultyId, int subjectId) {
        if (loadId <= 0 || facultyId <= 0 || subjectId <= 0) {
            PromptMessageView.errorMessage("Invalid load, faculty, or subject ID.");
            return false;
        }

        Load load = new Load(loadId, facultyId, subjectId);
        return loadRepo.update(load);
    }

    public boolean restoreLoad(int loadId) {
        if (loadId <= 0) {
            PromptMessageView.errorMessage("Invalid load ID.");
            return false;
        }

        return loadRepo.restore(loadId);
    }

    public boolean deleteLoad(int loadId) {
        if (loadId <= 0) {
            PromptMessageView.errorMessage("Invalid load ID.");
            return false;
        }

        return loadRepo.archive(loadId);
    }

    public boolean assignSubjectToFaculty(int facultyId, int subjectId) {
        if (facultyId <= 0 || subjectId <= 0) {
            PromptMessageView.errorMessage("Invalid faculty or subject ID.");
            return false;
        }

        return loadRepo.assignSubjectToFaculty(facultyId, subjectId);
    }

    public boolean removeSubjectFromFaculty(int facultyId, int subjectId) {
        if (facultyId <= 0 || subjectId <= 0) {
            PromptMessageView.errorMessage("Invalid faculty or subject ID.");
            return false;
        }

        return loadRepo.removeSubjectFromFaculty(facultyId, subjectId);
    }

    public List<Subject> getSubjectsByFacultyId(int facultyId) {
        if (facultyId <= 0) {
            PromptMessageView.errorMessage("Invalid faculty ID.");
            return null;
        }

        return loadRepo.getSubjectsByFacultyId(facultyId);
    }

    // assign faculty to a subject
    public void assignFaculty(List<Faculty> faculties, Subject subject) {
        List<Faculty> qualified = FacultyFilter.filterBySubject(faculties, subject);
        Faculty bestFaculty = null;
        double highestScore = 0;
        Map<Subject, Faculty> assignments = new HashMap<>();

        for (Faculty faculty : qualified) {
            double score = calculateScore(faculty, subject);
            if (score > highestScore) {
                highestScore = score;
                bestFaculty = faculty;
            }
        }

        if (bestFaculty != null) {
            assignments.put(subject, bestFaculty);
            PromptMessageView.successMessage("Assigned " + bestFaculty.getFullname() + " to " + subject.getName()
                    + " | Score: " + highestScore);
        } else {
            PromptMessageView.errorMessage("No qualified faculty found for " + subject.getName());
        }
    }

    private double calculateScore(Faculty faculty, Subject subject) {
        double score = 0;

        if (faculty.getMajor().equalsIgnoreCase(subject.getRecommendedMajor())) {
            score += 10;
        }

        // Skipping trainings
        // for (String training : faculty.getTrainings()) {
        // if (subject.getRequiredTraining().contains(training)) {
        // score += 5;
        // }
        // }

        // Skipping subjects taught
        // if (faculty.getSubjectsTaught().contains(subject.getName())) {
        // score += 7;
        // }

        score += faculty.getStudentFeedbackScore();
        score += faculty.getYearsOfExperience();

        return score;
    }
}
