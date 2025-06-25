package edu.university.facultyloading.model;

import java.util.List;

public class Faculty extends User {

    private int facultyId;
    private int loadId;

    private String major;
    private int yearsOfExperience;
    private double studentFeedbackScore;
    private int isAvailable;

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

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public double getStudentFeedbackScore() {
        return studentFeedbackScore;
    }

    public void setStudentFeedbackScore(double studentFeedbackScore) {
        this.studentFeedbackScore = studentFeedbackScore;
    }

    public int isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Faculty{" + "facultyId=" + facultyId + ", loadId=" + loadId + ", major=" + major + ", yearsOfExperience=" + yearsOfExperience + ", studentFeedbackScore=" + studentFeedbackScore + ", isAvailable=" + isAvailable + '}';
    }
}
