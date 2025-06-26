package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.TaughtSubject; 

public interface TaughtSubjectRepo {

    public TaughtSubject fetchTaughtSubjects(int facultyId);

    public boolean addTaughtSubject(int facultyId, int subjectId);

    public boolean deleteTaughtSubject(int id);
}
