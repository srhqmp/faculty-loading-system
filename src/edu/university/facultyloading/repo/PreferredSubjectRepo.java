package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.PreferredSubject;
import java.util.List;

public interface PreferredSubjectRepo {

    public PreferredSubject fetchPreferredSubjects(int facultyId);

    public boolean addPreferredSubject(int facultyId, int subjectId);

    public boolean deletePreferredSubject(int id);
}
