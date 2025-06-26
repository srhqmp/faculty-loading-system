package edu.university.facultyloading.model;

public class Load {

    private int id;
    private int approvedByRegistrarId;
    private int facultyId;
    private int isApproved;

    public Load() {
    }

    public Load(int id, int approvedByRegistrarId, int facultyId, int isApproved) {
        this.id = id;
        this.approvedByRegistrarId = approvedByRegistrarId;
        this.facultyId = facultyId;
        this.isApproved = isApproved;
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

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    @Override
    public String toString() {
        return "Load{" + "id=" + id + ", approvedByRegistrarId=" + approvedByRegistrarId + ", facultyId=" + facultyId + ", isApproved=" + isApproved + '}';
    }

}
