package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Training;
import java.util.List;

public interface TrainingRepo {

    public Training fetchTraining(int id);

    public List<Training> fetchTrainings();

    public boolean createTraining(String title, String description);

    public boolean updateTraining(int id, String title, String description);

    public boolean deleteTraining(int id);
}
