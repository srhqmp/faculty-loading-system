package edu.university.facultyloading.controller;

import java.util.List;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.LoadRepo;
import edu.university.facultyloading.repo.SubjectRepo;
import edu.university.facultyloading.util.PromptMessageView;

public class SubjectController {

    private final SubjectRepo subjectRepo;
    private final LoadRepo loadRepo;

    public SubjectController(SubjectRepo subjectRepo, LoadRepo loadRepo) {
        this.subjectRepo = subjectRepo;
        this.loadRepo = loadRepo;
    }

    public boolean createSubject(String name, String description, String recommendedMajor, int complexityLevel) {
        if (isValid(name, description, recommendedMajor, complexityLevel)) {
            Subject subject = new Subject(0, name.trim(), description.trim(), recommendedMajor.trim(), complexityLevel);
            return subjectRepo.create(subject);
        }
        return false;
    }

    public Subject getSubject(int subjectId) {
        if (subjectId <= 0) {
            PromptMessageView.errorMessage("Invalid Subject ID.");
            return null;
        }

        return subjectRepo.getById(subjectId);
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = subjectRepo.getAll();
        for (Subject subject : subjects) {
            List<Faculty> assignedFaculties = loadRepo.getAvailableFacultiesBySubjectId(subject.getSubjectId());
            subject.setAssignedFaculties(assignedFaculties);
        }
        return subjects;
    }

    public boolean updateSubject(int subjectId, String name, String description, String recommendedMajor,
            int complexityLevel) {
        if (subjectId <= 0) {
            PromptMessageView.errorMessage("Invalid Subject ID.");
            return false;
        }

        if (isValid(name, description, recommendedMajor, complexityLevel)) {
            Subject subject = new Subject(subjectId, name.trim(), description.trim(), recommendedMajor.trim(),
                    complexityLevel);
            return subjectRepo.update(subject);
        }
        return false;
    }

    public boolean deleteSubject(int subjectId) {
        if (subjectId <= 0) {
            PromptMessageView.errorMessage("Invalid Subject ID.");
            return false;
        }
        return subjectRepo.archive(subjectId);
    }

    public boolean restoreSubject(int subjectId) {
        if (subjectId <= 0) {
            PromptMessageView.errorMessage("Invalid Subject ID.");
            return false;
        }
        return subjectRepo.restore(subjectId);
    }

    private boolean isValid(String name, String description, String recommendedMajor, int complexityLevel) {
        return name != null && !name.trim().isEmpty()
                && description != null && !description.trim().isEmpty()
                && recommendedMajor != null && !recommendedMajor.trim().isEmpty()
                && complexityLevel >= 1 && complexityLevel <= 10;
    }
}
