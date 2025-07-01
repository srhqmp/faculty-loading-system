package edu.university.facultyloading.model;

public class Load {

    private int id;
    private int facultyId;
    private int subjectId;

    public Load() {
    }

    public Load(int id, int facultyId) {
        this.id = id;
        this.facultyId = facultyId;
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Load{" + "id=" + id + ", facultyId=" + facultyId + '}';
    }

}
