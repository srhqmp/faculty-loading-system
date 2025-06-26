package edu.university.facultyloading.main;

import edu.university.facultyloading.controller.AuthController;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.repo.AdminRepo;
import edu.university.facultyloading.repo.FacultyRepo;
import edu.university.facultyloading.repo.RegistrarRepo;
import edu.university.facultyloading.repo_impl.AdminRepoImpl;
import edu.university.facultyloading.repo_impl.FacultyRepoImpl;
import edu.university.facultyloading.repo_impl.RegistrarRepoImpl;
import edu.university.facultyloading.util.DbConnection;
import edu.university.facultyloading.view.AuthView;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DbConnection db = new DbConnection();
        AdminRepo adminRepo = new AdminRepoImpl(db);
        FacultyRepo facultyRepo = new FacultyRepoImpl(db);
        RegistrarRepo registrarRepo = new RegistrarRepoImpl(db);

        AuthController authController = new AuthController(adminRepo, registrarRepo, facultyRepo);
        AuthView authView = new AuthView(authController, scanner);

        authView.showLoginMenu();
    }

}
