package edu.university.facultyloading.model;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends User {

    private int facultyId;
    private int loadId;

    private String major;
    private int yearsOfExperience;
    private double studentFeedbackScore;
    private int isAvailable;
    private boolean availability;
    private List<String> trainings;
    private List<String> subjectsTaught;

    public Faculty() {
    }

    public Faculty(int id, int facultyId, int loadId, String major, int yearsOfExperience, double studentFeedbackScore,
            int isAvailable, String username, String password, String firstName, String lastName, int role) {
        super(id, username, password, firstName, lastName, role);
        this.facultyId = facultyId;
        this.loadId = loadId;
        this.major = major;
        this.yearsOfExperience = yearsOfExperience;
        this.studentFeedbackScore = studentFeedbackScore;
        this.isAvailable = isAvailable;
    }

    public Faculty(int facultyId, int loadId, String major, int yearsOfExperience, double studentFeedbackScore,
            int isAvailable, String username, String password, String firstName, String lastName, int role) {
        super(username, password, firstName, lastName, role);
        this.facultyId = facultyId;
        this.loadId = loadId;
        this.major = major;
        this.yearsOfExperience = yearsOfExperience;
        this.studentFeedbackScore = studentFeedbackScore;
        this.isAvailable = isAvailable;
        this.trainings = new ArrayList<>();
        this.subjectsTaught = new ArrayList<>();
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getLoadId() {
        return loadId;
    }

    public void setLoadId(int loadId) {
        this.loadId = loadId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public double getStudentFeedbackScore() {
        return studentFeedbackScore;
    }

    public List<String> getTrainings() {
        return trainings;
    }

    public List<String> getSubjectsTaught() {
        return subjectsTaught;
    }

    public void setStudentFeedbackScore(double studentFeedbackScore) {
        this.studentFeedbackScore = studentFeedbackScore;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public int isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Faculty{" + "facultyId=" + facultyId + ", loadId=" + loadId + ", major=" + major
                + ", yearsOfExperience=" + yearsOfExperience + ", studentFeedbackScore=" + studentFeedbackScore
                + ", isAvailable=" + isAvailable + '}';
    }

}
