package edu.university.facultyloading.controller;

import java.util.List;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.SubjectRepo;

public class SubjectController {

    private final SubjectRepo subjectRepo;

    public SubjectController(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    public boolean createSubject(String name, String description, String recommendedMajor, int complexityLevel) {
        if (isValid(name, description, recommendedMajor, complexityLevel)) {
            Subject subject = new Subject(0, name.trim(), description.trim(), recommendedMajor.trim(), complexityLevel);
            return subjectRepo.create(subject);
        }
        return false;
    }

    public Subject getSubject(int subjectId) {
        return subjectRepo.getById(subjectId);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepo.getAll();
    }

    public boolean updateSubject(int subjectId, String name, String description, String recommendedMajor,
            int complexityLevel) {
        if (isValid(name, description, recommendedMajor, complexityLevel)) {
            Subject subject = new Subject(subjectId, name.trim(), description.trim(), recommendedMajor.trim(),
                    complexityLevel);
            return subjectRepo.update(subject);
        }
        return false;
    }

    public boolean deleteSubject(int subjectId) {
        return subjectRepo.archive(subjectId);
    }

    public boolean restoreSubject(int subjectId) {
        return subjectRepo.restore(subjectId);
    }

    public boolean hardDeleteSubject(int subjectId) {
        return subjectRepo.delete(subjectId);
    }

    private boolean isValid(String name, String description, String recommendedMajor, int complexityLevel) {
        return name != null && !name.trim().isEmpty()
                && description != null && !description.trim().isEmpty()
                && recommendedMajor != null && !recommendedMajor.trim().isEmpty()
                && complexityLevel >= 1 && complexityLevel <= 10;
    }
}
