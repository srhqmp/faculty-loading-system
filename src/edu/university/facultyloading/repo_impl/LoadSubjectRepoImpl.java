package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.LoadSubjectRepo;
import edu.university.facultyloading.util.DbConnection;
import java.util.List;

public class LoadSubjectRepoImpl implements LoadSubjectRepo {

    private final DbConnection dbConnection;

    public LoadSubjectRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Subject> fetchLoadSubjects(int loadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addLoadSubject(int loadId, int subjectId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteLoadSubjects(int loadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
