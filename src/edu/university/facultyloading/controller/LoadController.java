package edu.university.facultyloading.controller;

import edu.university.facultyloading.model.Load;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.LoadRepo;

import java.util.List;

public class LoadController {
    private final LoadRepo loadRepo;

    public LoadController(LoadRepo loadRepo) {
        this.loadRepo = loadRepo;
    }

    public boolean createLoad(int facultyId, int subjectId) {
        if (facultyId <= 0 || subjectId <= 0) {
            System.out.println("Invalid faculty or subject ID.");
            return false;
        }

        Load load = new Load(0, facultyId, subjectId);
        return loadRepo.create(load);
    }

    public Load getLoadById(int loadId) {
        if (loadId <= 0) {
            System.out.println("Invalid load ID.");
            return null;
        }

        return loadRepo.getById(loadId);
    }

    public List<Load> getAllLoads() {
        return loadRepo.getAll();
    }

    public List<Load> getLoadsByFacultyId(int facultyId) {
        if (facultyId <= 0) {
            System.out.println("Invalid faculty ID.");
            return null;
        }

        return loadRepo.getByFacultyId(facultyId);
    }

    public boolean updateLoad(int loadId, int facultyId, int subjectId) {
        if (loadId <= 0 || facultyId <= 0 || subjectId <= 0) {
            System.out.println("Invalid load, faculty, or subject ID.");
            return false;
        }

        Load load = new Load(loadId, facultyId, subjectId);
        return loadRepo.update(load);
    }

    public boolean restoreLoad(int loadId) {
        if (loadId <= 0) {
            System.out.println("Invalid load ID.");
            return false;
        }

        return loadRepo.restore(loadId);
    }

    public boolean deleteLoad(int loadId) {
        if (loadId <= 0) {
            System.out.println("Invalid load ID.");
            return false;
        }

        return loadRepo.archive(loadId);
    }

    public boolean assignSubjectToFaculty(int facultyId, int subjectId) {
        if (facultyId <= 0 || subjectId <= 0) {
            System.out.println("Invalid faculty or subject ID.");
            return false;
        }

        return loadRepo.assignSubjectToFaculty(facultyId, subjectId);
    }

    public boolean removeSubjectFromFaculty(int facultyId, int subjectId) {
        if (facultyId <= 0 || subjectId <= 0) {
            System.out.println("Invalid faculty or subject ID.");
            return false;
        }

        return loadRepo.removeSubjectFromFaculty(facultyId, subjectId);
    }

    public List<Subject> getSubjectsByFacultyId(int facultyId) {
        if (facultyId <= 0) {
            System.out.println("Invalid faculty ID.");
            return null;
        }

        return loadRepo.getSubjectsByFacultyId(facultyId);
    }
}
