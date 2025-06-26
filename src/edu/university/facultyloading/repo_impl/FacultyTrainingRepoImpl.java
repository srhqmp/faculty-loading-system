package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Training;
import edu.university.facultyloading.repo.FacultyTrainingRepo;
import edu.university.facultyloading.util.DbConnection;
import java.util.List;

public class FacultyTrainingRepoImpl implements FacultyTrainingRepo {

    private final DbConnection dbConnection;

    public FacultyTrainingRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Training> fetchFacultyTrainings(int facultyId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addFacultyTraining(int facultyId, String role, int trainingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateFacultyTraining(int id, String role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteFacultyTraining(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
