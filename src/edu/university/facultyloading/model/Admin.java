/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.university.facultyloading.model;

/**
 *
 * @author user
 */
public class Admin extends User {

    private int adminId;

    public Admin() {
    }

    public Admin(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, Role.ADMIN);
    }

    public Admin(int id, String username, String password, String firstName, String lastName) {
        super(id, username, password, firstName, lastName, Role.ADMIN);
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Admin{" + "adminId=" + adminId + '}';
    }

}
