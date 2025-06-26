package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Subject;
import java.util.List;

public interface TaughtSubjectRepo {

    public List<Subject> fetchTaughtSubjects(int facultyId);

    public boolean addTaughtSubject(int facultyId, int subjectId);

    public boolean deleteTaughtSubject(int id);
}
