package edu.university.facultyloading.controller;

import edu.university.facultyloading.repo.LoadRepo;
import edu.university.facultyloading.repo.SubjectRepo;
 
import java.util.List; 
import edu.university.facultyloading.model.Load;

public class LoadController {
    private final LoadRepo loadRepo;
    private final SubjectRepo subjectRepo;

    public LoadController(LoadRepo loadRepo,  SubjectRepo subjectRepo) {
        this.loadRepo = loadRepo; 
        this.subjectRepo = subjectRepo;
    }

    public boolean createFacultyLoad(int facultyId) {
        // validate facultyId
        if (facultyId == 0) {
            System.out.println("Invalid faculty id. Please try again.");
        }

        return loadRepo.createLoad(facultyId);
    }

    // get all loads
    public List<Load> getAllLoads() {
        return loadRepo.fetchLoads();
    }

    // view faculty load
    private Load getFacultyLoad(int facultyId) {
        return loadRepo.fetchLoad(facultyId);
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
