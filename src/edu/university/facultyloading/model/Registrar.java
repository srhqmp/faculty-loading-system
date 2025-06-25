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

    public Registrar() {
    }

    public Registrar(int id, String username, String password, String firstName, String lastName) {
        super(id, username, password, firstName, lastName, 2);
    }

    public Registrar(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, 2);
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
