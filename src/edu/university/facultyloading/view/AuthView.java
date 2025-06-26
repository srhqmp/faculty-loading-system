package edu.university.facultyloading.view;

import edu.university.facultyloading.controller.AuthController;
import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Registrar;

import java.util.Scanner;

public class AuthView {

    private final AuthController authController;
    private final Scanner scanner;

    public AuthView(AuthController authController, Scanner scanner) {
        this.authController = authController;
        this.scanner = scanner;
    }

    public void showLoginMenu() {
        System.out.println("\n== Faculty Loading System ==");
        System.out.println("1. Login as Admin");
        System.out.println("2. Login as Faculty");
        System.out.println("3. Login as Registrar");
        System.out.println("4. Register New User");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                loginAsAdmin();
                break;
            case 2:
                loginAsFaculty();
                break;
            case 3:
                loginAsRegistrar();
                break;
            case 4:
                registerUser();
                break;
            case 5: {
                System.out.println("Exiting...");
                return;
            }
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void loginAsAdmin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Admin admin = authController.loginAdmin(username, password);
        if (admin != null) {
            System.out.println("Admin login successful. Welcome, " + admin.getFirstName() + "!");
        } else {
            System.out.println("Login failed.");
        }
    }

    private void loginAsFaculty() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Faculty faculty = authController.loginFaculty(username, password);
        if (faculty != null) {
            System.out.println("Faculty login successful. Welcome, " + faculty.getFirstName() + "!");
        } else {
            System.out.println("Login failed.");
        }
    }

    private void loginAsRegistrar() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Registrar registrar = authController.loginRegistrar(username, password);
        if (registrar != null) {
            System.out.println("Registrar login successful. Welcome, " + registrar.getFirstName() + "!");
        } else {
            System.out.println("Login failed.");
        }
    }

    private void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Select role (1 - Faculty, 2 - Registrar, 3 - Admin): ");
        int role = Integer.parseInt(scanner.nextLine());

        boolean success = authController.registerUser(username, password, firstName, lastName, role);
        if (success) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Please check your input.");
        }
    }
}
