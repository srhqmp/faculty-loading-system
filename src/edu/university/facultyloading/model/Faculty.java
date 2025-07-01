package edu.university.facultyloading.model;

import java.util.List;

public class Faculty extends User {

    private int facultyId;
    private String major;
    private int yearsOfExperience;
    private double studentFeedbackScore;
    private boolean isAvailable;

    private List<Subject> assignedSubjects;

    public Faculty() {
        super();
    }

    public Faculty(
            int facultyId, int userId, String username, String password,
            String firstName, String lastName,
            String major, int yearsOfExperience, double studentFeedbackScore, boolean isAvailable) {
        super(userId, username, password, firstName, lastName, 1); // role = 1
        this.facultyId = facultyId;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public List<Subject> getAssignedSubjects() {
        return assignedSubjects;
    }

    public void setAssignedSubjects(List<Subject> assignedSubjects) {
        this.assignedSubjects = assignedSubjects;
    }

    @Override
    public String toString() {
        return "Faculty [facultyId=" + facultyId + ", major=" + major + ", yearsOfExperience=" + yearsOfExperience
                + ", studentFeedbackScore=" + studentFeedbackScore + ", isAvailable=" + isAvailable
                + ", assignedSubjects=" + assignedSubjects + "]";
    }

}
