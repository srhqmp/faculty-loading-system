package edu.university.facultyloading.main;

import edu.university.facultyloading.controller.AdminController;
import edu.university.facultyloading.controller.FacultyController;
import edu.university.facultyloading.controller.RegistrarController;
import edu.university.facultyloading.repo.AdminRepo;
import edu.university.facultyloading.repo.FacultyRepo;
import edu.university.facultyloading.repo.RegistrarRepo;
import edu.university.facultyloading.repo_impl.AdminRepoImpl;
import edu.university.facultyloading.repo_impl.FacultyRepoImpl;
import edu.university.facultyloading.repo_impl.RegistrarRepoImpl;
import edu.university.facultyloading.util.DbConnection;
import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.view.LoginView;
import edu.university.facultyloading.view.MainMenuView;
import edu.university.facultyloading.view.RegisterUserView;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // utils
        DbConnection db = new DbConnection();
        OutputFormatter outputFormatter = new OutputFormatter();

        // repo
        AdminRepo adminRepo = new AdminRepoImpl(db);
        AdminController adminController = new AdminController(adminRepo);
        FacultyRepo facultyRepo = new FacultyRepoImpl(db);
        FacultyController facultyController = new FacultyController(facultyRepo);
        RegistrarRepo registrarRepo = new RegistrarRepoImpl(db);
        RegistrarController registrarController = new RegistrarController(registrarRepo);

        // views
        LoginView loginView = new LoginView(scanner, outputFormatter, adminController, facultyController, registrarController);
        RegisterUserView registerView = new RegisterUserView(scanner, outputFormatter, adminController, facultyController, registrarController);
        MainMenuView mainMenuView = new MainMenuView(scanner, outputFormatter, loginView, registerView);

        // show main menu
        mainMenuView.show();
    }

}
