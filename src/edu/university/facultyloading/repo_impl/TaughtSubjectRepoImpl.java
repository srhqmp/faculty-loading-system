package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.TaughtSubjectRepo;
import edu.university.facultyloading.util.DbConnection;
import java.util.List;

public class TaughtSubjectRepoImpl implements TaughtSubjectRepo {

    private final DbConnection dbConnection;

    public TaughtSubjectRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Subject> fetchTaughtSubjects(int facultyId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addTaughtSubject(int facultyId, int subjectId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteTaughtSubject(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
