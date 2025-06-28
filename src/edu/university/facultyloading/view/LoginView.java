package edu.university.facultyloading.view;

import edu.university.facultyloading.controller.AdminController;
import edu.university.facultyloading.controller.FacultyController;
import edu.university.facultyloading.controller.RegistrarController;
import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Registrar;
import edu.university.facultyloading.util.OutputFormatter;
import java.util.Scanner;

public class LoginView {

    private final Scanner scanner;
    private final AdminController adminController;
    private final FacultyController facultyController;
    private final RegistrarController registrarController;

    public LoginView(Scanner scanner, AdminController adminController, FacultyController facultyController, RegistrarController registrarController) {
        this.scanner = scanner;
        this.adminController = adminController;
        this.facultyController = facultyController;
        this.registrarController = registrarController;
    }

    public void showLoginPrompt() {
        System.out.println("\n=== Login ===");

        String choice;
        while (true) {
            System.out.println("Select user type:");
            System.out.println("1. Admin");
            System.out.println("2. Registrar");
            System.out.println("3. Faculty");
            System.out.print("Enter choice: ");
            choice = scanner.nextLine().trim();
            System.out.println();

            if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                break; // valid input, proceed
            } else {
                System.out.println("Invalid input. Please try again.\n");
            }
        }

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.println();

        switch (choice) {
            case "1":
                loginAdmin(username, password);
                break;
            case "2":
                loginRegistrar(username, password);
                break;
            case "3":
                loginFaculty(username, password);
                break;
        }
    }

    private void loginAdmin(String username, String password) {
        Admin admin = adminController.login(username, password);

        if (admin == null) {
            System.out.println("Failed to login. Please try again.");
            return;
        }

        System.out.println("Welcome " + admin.getFirstName() + " " + admin.getLastName() + "!");
    }

    private void loginFaculty(String username, String password) {
        Faculty faculty = facultyController.login(username, password);

        if (faculty == null) {
            System.out.println("Failed to login. Please try again.");
            return;
        }

        System.out.println("Welcome " + faculty.getFirstName() + " " + faculty.getLastName() + "!");
    }

    private void loginRegistrar(String username, String password) {
        Registrar registrar = registrarController.login(username, password);

        if (registrar == null) {
            System.out.println("Failed to login. Please try again.");
            return;
        }

        System.out.println("Welcome " + registrar.getFirstName() + " " + registrar.getLastName() + "!");
    }

}
