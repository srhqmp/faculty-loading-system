package edu.university.facultyloading.model;

import java.util.List;

public class Faculty extends User {

    private int facultyId;

    private String major;
    private int yearsOfExperience;
    private boolean isAvailable;
    private List<Training> trainings;
    private List<Subject> preferredSubjects;
    private List<Subject> taughtSubjects;
    private double studentFeedbackScore;
    private Load load;
    private Admin approvedBy;

    public Faculty() {
    }

    public Faculty(int facultyId, int userId, String username, String password, String firstName, String lastName,
            String major, int yearsOfExperience,
            double studentFeedbackScore, boolean isAvailable) {
        super(userId, username, password, firstName, lastName, Role.FACULTY);
        this.facultyId = facultyId;
        this.major = major;
        this.yearsOfExperience = yearsOfExperience;
        this.studentFeedbackScore = studentFeedbackScore;
        this.isAvailable = isAvailable;
    }

    public Faculty(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, Role.FACULTY);
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

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public List<Subject> getPreferredSubjects() {
        return preferredSubjects;
    }

    public void setPreferredSubjects(List<Subject> preferredSubjects) {
        this.preferredSubjects = preferredSubjects;
    }

    public Load getLoad() {
        return load;
    }

    public void setLoad(Load load) {
        this.load = load;
    }

    public List<Subject> getTaughtSubjects() {
        return taughtSubjects;
    }

    public void setTaughtSubjects(List<Subject> taughtSubjects) {
        this.taughtSubjects = taughtSubjects;
    }

    public Admin getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Admin approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Override
    public String toString() {
        return "Faculty{" + "facultyId=" + facultyId + ", major=" + major + ", yearsOfExperience=" + yearsOfExperience + ", isAvailable=" + isAvailable + ", trainings=" + trainings + ", preferredSubjects=" + preferredSubjects + ", taughtSubjects=" + taughtSubjects + ", studentFeedbackScore=" + studentFeedbackScore + ", load=" + load + ", approvedBy=" + approvedBy + '}';
    }

}
