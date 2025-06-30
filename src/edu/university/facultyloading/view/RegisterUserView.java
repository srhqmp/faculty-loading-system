package edu.university.facultyloading.view;

import edu.university.facultyloading.controller.AdminController;
import edu.university.facultyloading.controller.AppController;
import edu.university.facultyloading.controller.FacultyController;
import edu.university.facultyloading.controller.RegistrarController;
import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Registrar;

import java.util.Scanner;

public class RegisterUserView {

    private final Scanner scanner;
    private final AdminController adminController;
    private final FacultyController facultyController;
    private final RegistrarController registrarController;
    private AppController appController;

    public RegisterUserView(Scanner scanner, AdminController adminController, FacultyController facultyController,
            RegistrarController registrarController) {
        this.scanner = scanner;
        this.adminController = adminController;
        this.facultyController = facultyController;
        this.registrarController = registrarController;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void showRegisterPrompt() {
        while (true) {
            System.out.println("\n=== Register New User ===");

            String choice;
            while (true) {
                System.out.println("Select user type:");
                System.out.println("1. Admin");
                System.out.println("2. Registrar");
                System.out.println("3. Faculty");
                System.out.println("0. Return to Main Menu");
                System.out.print("Enter choice: ");
                choice = scanner.nextLine().trim();
                System.out.println();

                if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("0")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please try again.\n");
                }
            }

            if (choice.equals("0")) {
                appController.goToMainMenu();
                return;
            }

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
                    if (success) {
                        Admin admin = adminController.login(username, password);
                        appController.goToAdminDashboard(admin);
                        return;
                    }
                    break;

                case "2":
                    success = registrarController.register(username, password, firstName, lastName);
                    if (success) {
                        Registrar registrar = registrarController.login(username, password);
                        // TODO: Redirect to registrar dashboard
                        System.out.println("Registrar registered. Dashboard not yet implemented.");
                        return;
                    }
                    break;

                case "3":
                    success = facultyController.register(username, password, firstName, lastName);
                    if (success) {
                        Faculty faculty = facultyController.login(username, password);
                        appController.goToFacultyDashboard(faculty);
                        return;
                    }
                    break;
            }

            System.out.println("Registration failed. Please try again.\n");
        }
    }

}
