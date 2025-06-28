/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.university.facultyloading.controller;

import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.SubjectRepo;
import java.util.List;

public class SubjectController {

    private final SubjectRepo subjectRepo;

    public SubjectController(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    public boolean createSubject(String name, String description) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Subject name cannot be empty.");
            return false;
        }

        return subjectRepo.createSubject(name, description != null ? description.trim() : "");
    }

    public boolean updateSubject(int id, String name, String description) {
        if (id <= 0) {
            System.out.println("Invalid subject ID.");
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Subject name cannot be empty.");
            return false;
        }
        return subjectRepo.updateSubject(id, name, description != null ? description.trim() : "");
    }

    public boolean deleteSubject(int id) {
        if (id <= 0) {
            System.out.println("Invalid subject ID.");
            return false;
        }
        return subjectRepo.archiveSubject(id);
    }

    public boolean restoreSubject(int id) {
        if (id <= 0) {
            System.out.println("Invalid subject ID.");
            return false;
        }
        return subjectRepo.restoreSubject(id);
    }

    public Subject getSubject(int id) {
        if (id <= 0) {
            System.out.println("Invalid subject ID.");
            return null;
        }
        return subjectRepo.fetchSubject(id);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepo.fetchSubjects();
    }
}
