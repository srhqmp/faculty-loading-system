package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.PreferredSubjectRepo;
import edu.university.facultyloading.util.DbConnection;
import java.util.List;

public class PreferredSubjectRepoImpl implements PreferredSubjectRepo {

    private final DbConnection dbConnection;

    public PreferredSubjectRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Subject> fetchPreferredSubjects(int facultyId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addPreferredSubject(int facultyId, int subjectId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePreferredSubject(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
