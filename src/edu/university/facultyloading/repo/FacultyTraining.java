package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Training;
import java.util.List;

public interface FacultyTraining {

    public List<Training> fetchFacultyTrainings(int facultyId);

    public boolean addFacultyTraining(int facultyId, String role, int trainingId);

    public boolean updateFacultyTraining(int id, String role);

    public boolean deleteFacultyTraining(int id);
}
