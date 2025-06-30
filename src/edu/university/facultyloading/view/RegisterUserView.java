package edu.university.facultyloading.view;

import edu.university.facultyloading.controller.AdminController;
import edu.university.facultyloading.controller.AppController;
import edu.university.facultyloading.controller.FacultyController;
import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;

import edu.university.facultyloading.util.OutputFormatter;
import java.util.Scanner;

public class RegisterUserView {

    private final Scanner scanner;
    private final AdminController adminController;
    private final FacultyController facultyController;
    private AppController appController;

    public RegisterUserView(Scanner scanner, AdminController adminController, FacultyController facultyController) {
        this.scanner = scanner;
        this.adminController = adminController;
        this.facultyController = facultyController;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void showRegisterPrompt() {
        System.out.println();
        System.out.println(OutputFormatter.centerString("╔════════════════════════════════════╗"));
        System.out.println(OutputFormatter.centerString("║           Register User            ║"));
        System.out.println(OutputFormatter.centerString("╚════════════════════════════════════╝"));
        System.out.println();

        String choice;
        while (true) {
            while (true) {
                System.out.println(OutputFormatter.centerString("╔══════════════════════════════╗"));
                System.out.println(OutputFormatter.centerString("║    Select User Type Below    ║"));
                System.out.println(OutputFormatter.centerString("╠══════════════════════════════╣"));
                System.out.println(OutputFormatter.centerString("║ 1. Admin                     ║"));
                System.out.println(OutputFormatter.centerString("║ 2. Faculty                   ║"));
                System.out.println(OutputFormatter.centerString("║ 0. Return to Main Menu       ║"));
                System.out.println(OutputFormatter.centerString("╚══════════════════════════════╝"));
                System.out.print(OutputFormatter.centerString("Enter choice [1-3]: "));
                choice = scanner.nextLine().trim();
                System.out.println();

                if (choice.equals("1") || choice.equals("2") || choice.equals("0")) {
                    break; // valid choice
                } else {
                    System.out.println("Invalid input. Please try again.\n");
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
                    success = facultyController.register(username, password, firstName, lastName);
                    break;
                case "0":
                    appController.goToMainMenu();
                    break;
            }

            if (success) {
                if (choice == "1") {
                    Admin admin = adminController.login(username, password);
                    appController.goToAdminDashboard(admin);
                } else if (choice == "2") {
                    Faculty faculty = facultyController.login(username, password);
                    appController.goToFacultyDashboard(faculty);
                }
            } else {
                System.out.println("Registration failed. Please check your inputs.");
            }
        }
    }
}
