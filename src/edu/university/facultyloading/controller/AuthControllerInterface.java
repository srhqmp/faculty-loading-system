package edu.university.facultyloading.controller;

import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Registrar;

public interface AuthControllerInterface {

    public Admin loginAdmin(String username, String password);

    public Faculty loginFaculty(String username, String password);

    public Registrar loginRegistrar(String username, String password);

    public boolean registerUser(String username, String password, String firstName, String lastName, int role);

}
