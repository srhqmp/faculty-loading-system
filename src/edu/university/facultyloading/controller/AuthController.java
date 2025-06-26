package edu.university.facultyloading.controller;

import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Registrar;
import edu.university.facultyloading.repo.AdminRepo;
import edu.university.facultyloading.repo.FacultyRepo;
import edu.university.facultyloading.repo.RegistrarRepo;

public class AuthController implements AuthControllerInterface {

    private final AdminRepo adminRepo;
    private final RegistrarRepo registrarRepo;
    private final FacultyRepo facultyRepo;

    public AuthController(AdminRepo adminRepo, RegistrarRepo registrarRepo, FacultyRepo facultyRepo) {
        this.adminRepo = adminRepo;
        this.registrarRepo = registrarRepo;
        this.facultyRepo = facultyRepo;
    }

    @Override
    public Admin loginAdmin(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        return adminRepo.fetchAdmin(username, password);
    }

    @Override
    public Faculty loginFaculty(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        return facultyRepo.fetchFaculty(username, password);
    }

    @Override
    public Registrar loginRegistrar(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        return registrarRepo.fetchRegistrar(username, password);
    }

    /*
    Role:
    1 - faculty
    2 - registar
    3 - admin
     */
    @Override
    public boolean registerUser(String username, String password, String firstName, String lastName, int role) {
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()
                || firstName == null || firstName.trim().isEmpty()
                || lastName == null || lastName.trim().isEmpty()
                || role == 0 || role > 3) {
            return false;
        }

        switch (role) {
            case 1:
                return facultyRepo.createFaculty(username, password, firstName, lastName);
            case 2:
                return registrarRepo.createRegistrar(username, password, firstName, lastName);
            case 3:
                return adminRepo.createAdmin(username, password, firstName, lastName);
            default:
                return false;
        }
    }

}
