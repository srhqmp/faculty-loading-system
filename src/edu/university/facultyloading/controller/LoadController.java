package edu.university.facultyloading.controller;

import edu.university.facultyloading.repo.LoadRepo;
import edu.university.facultyloading.repo.LoadSubjectRepo;
import edu.university.facultyloading.repo.SubjectRepo;

import java.util.ArrayList;
import java.util.List;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.model.Load;
import edu.university.facultyloading.model.LoadSubject;

public class LoadController {
    private final LoadRepo loadRepo;
    private final LoadSubjectRepo loadSubjectRepo;
    private final SubjectRepo subjectRepo;

    public LoadController(LoadRepo loadRepo, LoadSubjectRepo loadSubjectRepo, SubjectRepo subjectRepo) {
        this.loadRepo = loadRepo;
        this.loadSubjectRepo = loadSubjectRepo;
        this.subjectRepo = subjectRepo;
    }

    public boolean createFacultyLoad(int facultyId) {
        // validate facultyId
        if (facultyId == 0) {
            System.out.println("Invalid faculty id. Please try again.");
        }

        return loadRepo.createLoad(facultyId);
    }

    // get load subjects per faculty
    public List<Subject> getLoadSubjects(int facultyId) {
        // validate faculty id
        if (facultyId == 0) {
            System.out.println("Invalid faculty id. Please try again.");
        }

        Load facultyLoad = getFacultyLoad(facultyId);
        int loadId = facultyLoad.getId();
        LoadSubject loadSubject = loadSubjectRepo.fetchLoadSubjects(loadId);

        // return load subjects
        return loadSubject.getSubjects();
    }

    // add load subject
    public boolean addLoadSubject(int loadId, int subjectId) {
        // validate id's
        if (loadId == 0) {
            System.out.println("Invalid faculty id. Please try again.");
        }
        if (subjectId == 0) {
            System.out.println("Invalid subject id. Please try again.");
        }

        // check if subject exist
        Subject subject = subjectRepo.fetchSubject(subjectId);
        if (subject.getId() == 0) {
            System.out.println("Subject does not exist.");
        }
        // add load subject
        return loadSubjectRepo.addLoadSubject(loadId, subjectId);


    }

    // get all filtered loads
    public List<Load> getFilteredLoads(int isApproved) {
        List<Load> loads = loadRepo.fetchLoads();
        List<Load> filteredLoads = new ArrayList<>();

        for (Load load : loads) {
            if (load.getIsApproved() == isApproved) {
                filteredLoads.add(load);
            }
        }

        return filteredLoads;
    }

    // get all loads
    public List<Load> getAllLoads() {
        return loadRepo.fetchLoads();
    }

    // view faculty load
    private Load getFacultyLoad(int facultyId) {
        return loadRepo.fetchLoad(facultyId);
    }

    // approve load
    public boolean approveFacultyLoad(int facultyId, int registrarId) {
        // validate id's
        if (facultyId == 0) {
            System.out.println("Invalid faculty id. Please try again.");
        }
        if (registrarId == 0) {
            System.out.println("Invalid registrar id. Please try again.");
        }

        // get load id
        Load load = getFacultyLoad(facultyId);
        int loadId = load.getId();
        // approve load
        return loadRepo.approveLoad(loadId, registrarId);
    }

    // delete load
    public boolean deleteFacultyLoad(int facultyId) {
        // validate id
        if (facultyId == 0) {
            System.out.println("Invalid faculty id. Please try again.");
        }
        // get load
        Load load = getFacultyLoad(facultyId);
        int loadId = load.getId();
        // delete load
        return loadRepo.archiveLoad(loadId);
    }
}
