package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Load;
import edu.university.facultyloading.model.Subject;

import java.util.List;

public interface LoadRepo {
    void create(Load load);

    Load getById(int loadId);

    List<Load> getAll();

    List<Load> getByFacultyId(int facultyId);

    void update(Load load);

    void archive(int loadId);

    void restore(int loadId);

    void delete(int loadId);

    boolean assignSubjectToFaculty(int facultyId, int subjectId);

    List<Subject> getSubjectsByFacultyId(int facultyId); 
    
    boolean removeSubjectFromFaculty(int facultyId, int subjectId);
}
