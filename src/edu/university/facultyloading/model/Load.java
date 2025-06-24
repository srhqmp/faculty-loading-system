package edu.university.facultyloading.model;

import java.util.List;

public class Load {

    private int id;
    private int approvedByRegistrarId;
    private boolean isApproved;
    private List<Subject> subjects;

    public Load() {
    }

    public Load(int id, int approvedByRegistrarId, boolean isApproved, List<Subject> subjects) {
        this.id = id;
        this.approvedByRegistrarId = approvedByRegistrarId;
        this.isApproved = isApproved;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApprovedByRegistrarId() {
        return approvedByRegistrarId;
    }

    public void setApprovedByRegistrarId(int approvedByRegistrarId) {
        this.approvedByRegistrarId = approvedByRegistrarId;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Load{" + "id=" + id + ", approvedByRegistrarId=" + approvedByRegistrarId + ", isApproved=" + isApproved + ", subjects=" + subjects + '}';
    }

}
