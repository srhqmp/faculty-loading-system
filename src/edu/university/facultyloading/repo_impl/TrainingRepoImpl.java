package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Training;
import edu.university.facultyloading.repo.TrainingRepo;
import edu.university.facultyloading.util.DbConnection;
import java.util.List;

public class TrainingRepoImpl implements TrainingRepo {

    private final DbConnection dbConnection;

    public TrainingRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Training fetchTraining(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Training> fetchTrainings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createTraining(String title, String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateTraining(int id, String title, String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteTraining(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
