package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Load;
import edu.university.facultyloading.model.Subject;

import java.util.List;

public interface LoadRepo {
    boolean create(Load load);

    Load getById(int loadId);

    List<Load> getAll();

    List<Load> getByFacultyId(int facultyId);

    boolean update(Load load);

    boolean archive(int loadId);

    boolean restore(int loadId);

    boolean delete(int loadId);

    boolean assignSubjectToFaculty(int facultyId, int subjectId);

    List<Subject> getSubjectsByFacultyId(int facultyId); 
    
    boolean removeSubjectFromFaculty(int facultyId, int subjectId);
}
