package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Subject;
import java.util.List;

public interface PreferredSubjectRepo {

    public List<Subject> fetchPreferredSubjects(int facultyId);

    public boolean addPreferredSubject(int facultyId, int subjectId);

    public boolean deletePreferredSubject(int id);
}
