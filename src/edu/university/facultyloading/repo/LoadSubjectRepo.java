package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.LoadSubject;

public interface LoadSubjectRepo {

    public LoadSubject fetchLoadSubjects(int loadId);

    public boolean addLoadSubject(int loadId, int subjectId);

    public boolean deleteLoadSubjects(int loadId);
}
