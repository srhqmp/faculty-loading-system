package edu.university.facultyloading.model;

import java.util.List;

public class TaughtSubject {

    private int id;
    private int facultyId;
    private List<Subject> subjects;

    public TaughtSubject() {
    }

    public TaughtSubject(int id, int facultyId, List<Subject> subjects) {
        this.id = id;
        this.facultyId = facultyId;
        this.subjects = subjects;
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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "TaughtSubject{" + "id=" + id + ", facultyId=" + facultyId + ", subjects=" + subjects + '}';
    }

}
