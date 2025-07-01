package edu.university.facultyloading.model;

public class Admin extends User {

    private int adminId;

    public Admin() {
        super();
    }

    public Admin(int adminId, int userId, String username, String password, String firstName, String lastName) {
        super(userId, username, password, firstName, lastName, 2); // role = 2
        this.adminId = adminId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Admin [adminId=" + adminId + "]";
    }

}
