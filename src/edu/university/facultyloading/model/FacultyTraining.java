package edu.university.facultyloading.model;

import java.util.List;

public class FacultyTraining {

    private int id;
    private int facultyId;
    private String role;
    private List<Training> trainings;

    public FacultyTraining() {
    }

    public FacultyTraining(int id, int facultyId, String role, List<Training> trainings) {
        this.id = id;
        this.facultyId = facultyId;
        this.role = role;
        this.trainings = trainings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    @Override
    public String toString() {
        return "FacultyTraining{" + "id=" + id + ", facultyId=" + facultyId + ", role=" + role + ", trainings=" + trainings + '}';
    }

}
