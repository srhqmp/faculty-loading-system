package edu.university.facultyloading.model;

public class Load {
    private int loadId;
    private int facultyId;
    private int subjectId;

    public Load() {
    }

    public Load(int loadId, int facultyId, int subjectId) {
        this.loadId = loadId;
        this.facultyId = facultyId;
        this.subjectId = subjectId;
    }

    public int getLoadId() {
        return loadId;
    }

    public void setLoadId(int loadId) {
        this.loadId = loadId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Load [loadId=" + loadId + ", facultyId=" + facultyId + ", subjectId=" + subjectId + "]";
    }

}
