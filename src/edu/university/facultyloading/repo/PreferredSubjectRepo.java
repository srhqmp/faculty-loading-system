package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.PreferredSubject;

public interface PreferredSubjectRepo {

    public PreferredSubject fetchPreferredSubjects(int facultyId);

    public boolean addPreferredSubject(int facultyId, int subjectId);

    public boolean deletePreferredSubject(int id);
}
