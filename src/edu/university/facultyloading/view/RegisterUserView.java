package edu.university.facultyloading.view;

import edu.university.facultyloading.controller.AdminController;
import edu.university.facultyloading.controller.FacultyController;
import edu.university.facultyloading.controller.RegistrarController;
import java.util.Scanner;

public class RegisterUserView {

    private final Scanner scanner;
    private final AdminController adminController;
    private final FacultyController facultyController;
    private final RegistrarController registrarController;

    public RegisterUserView(Scanner scanner, AdminController adminController, FacultyController facultyController, RegistrarController registrarController) {
        this.scanner = scanner;
        this.adminController = adminController;
        this.facultyController = facultyController;
        this.registrarController = registrarController;
    }

    public void showRegisterPrompt() {
        System.out.println("\n=== Register New User ===");

        String choice;
        while (true) {
            System.out.println("Select user type:");
            System.out.println("1. Admin");
            System.out.println("2. Registrar");
            System.out.println("3. Faculty");
            System.out.print("Enter choice: ");
            choice = scanner.nextLine().trim();

            if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                break; // valid choice
            } else {
                System.out.println("Invalid input. Please enter 1, 2, or 3.\n");
            }
        }

        System.out.println();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.println();

        boolean success = false;

        switch (choice) {
            case "1":
                success = adminController.register(username, password, firstName, lastName);
                break;
            case "2":
                success = registrarController.register(username, password, firstName, lastName);
                break;
            case "3":
                success = facultyController.register(username, password, firstName, lastName);
                break;
        }

        if (success) {
            System.out.println("Registration successful! You may now login.");
        } else {
            System.out.println("Registration failed. Please check your inputs.");
        }
    }

}
