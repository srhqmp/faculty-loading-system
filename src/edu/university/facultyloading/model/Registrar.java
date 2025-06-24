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
public class Registrar extends User {

    private int registrarId;

    public Registrar(int registrarId, int userId, String username, String password, String firstName, String lastName) {
        super(userId, username, password, firstName, lastName, Role.REGISTRAR);
        this.registrarId = registrarId;
    }

    public Registrar(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, Role.REGISTRAR);
    }

    public int getRegistrarId() {
        return registrarId;
    }

    public void setRegistrarId(int registrarId) {
        this.registrarId = registrarId;
    }

    @Override
    public String toString() {
        return "Registrar{" + "registrarId=" + registrarId + '}';
    }
}
