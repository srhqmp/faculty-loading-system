package edu.university.facultyloading.controller;

import java.util.List;

import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.SubjectRepo;

public class SubjectController {

    private final SubjectRepo subjectRepo;

    public SubjectController(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    public void createSubject(Subject subject) {
        subjectRepo.create(subject);
    }

    public Subject getSubject(int subjectId) {
        return subjectRepo.getById(subjectId);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepo.getAll();
    }

    public void updateSubject(Subject subject) {
        subjectRepo.update(subject);
    }

    public void deleteSubject(int subjectId) {
        subjectRepo.archive(subjectId);
    }
}
