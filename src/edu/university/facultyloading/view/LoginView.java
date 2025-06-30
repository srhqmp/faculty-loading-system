package edu.university.facultyloading.view;

import edu.university.facultyloading.controller.AdminController;
import edu.university.facultyloading.controller.AppController;
import edu.university.facultyloading.controller.FacultyController;
import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.util.OutputFormatter;
import java.util.Scanner;

public class LoginView {

    private final Scanner scanner;
    private final AdminController adminController;
    private final FacultyController facultyController;
    private AppController appController;

    public LoginView(Scanner scanner, AdminController adminController, FacultyController facultyController) {
        this.scanner = scanner;
        this.adminController = adminController;
        this.facultyController = facultyController;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void showLoginPrompt() {
        System.out.println();
        System.out.println(OutputFormatter.centerString("╔════════════════════════════════════╗"));
        System.out.println(OutputFormatter.centerString("║               LOG IN               ║"));
        System.out.println(OutputFormatter.centerString("╚════════════════════════════════════╝"));
        System.out.println();

        String choice;
        while (true) {
            System.out.println(OutputFormatter.centerString("╔══════════════════════════════╗"));
            System.out.println(OutputFormatter.centerString("║        SELECT USER TYPE      ║"));
            System.out.println(OutputFormatter.centerString("╠══════════════════════════════╣"));
            System.out.println(OutputFormatter.centerString("║ 1. Admin                     ║"));
            System.out.println(OutputFormatter.centerString("║ 2. Faculty                   ║"));
            System.out.println(OutputFormatter.centerString("║ 0. Return to Main Menu       ║"));
            System.out.println(OutputFormatter.centerString("╚══════════════════════════════╝"));
            System.out.print(OutputFormatter.centerString("Enter choice [1-3]: "));
            choice = scanner.nextLine().trim();
            System.out.println();

            if (choice.equals("1") || choice.equals("2") || choice.equals("0")) {
                break; // valid input, proceed
            } else {
                System.out.println("Invalid input. Please try again.\n");
            }
        }

        // return to main menu if choice is 0
        if (choice.equals("0")) {
            appController.goToMainMenu();
            return;
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
                loginFaculty(username, password);
                break;
            default:
                return;
        }
    }

    private void loginAdmin(String username, String password) {
        Admin admin = adminController.login(username, password);

        if (admin == null) {
            System.out.println("Failed to login. Please try again.");
            return;
        }

        System.out.println("Welcome " + admin.getFirstName() + " " + admin.getLastName() + "!");
        appController.goToAdminDashboard(admin);
    }

    private void loginFaculty(String username, String password) {
        Faculty faculty = facultyController.login(username, password);

        if (faculty == null) {
            System.out.println("Failed to login. Please try again.");
            return;
        }

        System.out.println("Welcome " + faculty.getFirstName() + " " + faculty.getLastName() + "!");
        appController.goToFacultyDashboard(faculty);
    }
}
