package edu.university.facultyloading.controller;

import edu.university.facultyloading.model.Registrar;
import edu.university.facultyloading.repo.RegistrarRepo;

public class RegistrarController {

    private final RegistrarRepo registrarRepo;

    public RegistrarController(RegistrarRepo registrarRepo) {
        this.registrarRepo = registrarRepo;
    }

    public Registrar login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        return registrarRepo.fetchRegistrar(username, password);
    }

    public boolean register(String username, String password, String firstName, String lastName) {
        // validate user input
        if (username == null || username.trim().isEmpty()) {
            return false;
        } else if (password == null || password.trim().isEmpty()) {
            return false;
        } else if (firstName == null || firstName.trim().isEmpty()) {
            return false;
        } else if (lastName == null || lastName.trim().isEmpty()) {
            return false;
        } else if (!registrarRepo.isUsernameUnique(username)) {
            System.out.println("A user with username " + username + " already exists.");
            return false;
        } else {
            return registrarRepo.createRegistrar(username, password, firstName, lastName);
        }
    }

    // get all faculty load report

    // get faculty teaching load

    // approve teaching load

}
