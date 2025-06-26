package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.FacultyTraining;

public interface FacultyTrainingRepo {

    public FacultyTraining fetchFacultyTrainings(int facultyId);

    public boolean addFacultyTraining(int facultyId, String role, int trainingId);

    public boolean updateFacultyTraining(int id, String role);

    public boolean deleteFacultyTraining(int id);
}
