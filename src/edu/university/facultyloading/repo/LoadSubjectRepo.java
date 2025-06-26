package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Subject;
import java.util.List;

public interface LoadSubjectRepo {

    public List<Subject> fetchLoadSubjects(int loadId);

    public boolean addLoadSubject(int loadId, int subjectId);

    public boolean deleteLoadSubjects(int loadId);
}
