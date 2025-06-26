package edu.university.facultyloading.model;

import java.util.List;

public class LoadSubject {

    private int id;
    private int loadId;
    private List<Subject> subjects;

    public LoadSubject() {
    }

    public LoadSubject(int id, int loadId, List<Subject> subjects) {
        this.id = id;
        this.loadId = loadId;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoadId() {
        return loadId;
    }

    public void setLoadId(int loadId) {
        this.loadId = loadId;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "LoadSubject{" + "id=" + id + ", loadId=" + loadId + ", subjects=" + subjects + '}';
    }

}
