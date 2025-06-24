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

    public Admin() {
    }

    public Admin(int id, String username, String password, String firstName, String lastName, Role role) {
        super(id, username, password, firstName, lastName, role);
    }
}
