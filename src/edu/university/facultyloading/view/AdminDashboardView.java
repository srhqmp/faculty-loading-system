package edu.university.facultyloading.view;

import java.util.Scanner;

import edu.university.facultyloading.controller.AdminController;
import edu.university.facultyloading.controller.AppController;
import edu.university.facultyloading.model.Admin;

public class AdminDashboardView {

    private final Scanner scanner;
    private AppController appController;
    private final AdminController adminController;

    public AdminDashboardView(Scanner scanner, AdminController adminController) {
        this.scanner = scanner;
        this.adminController = adminController;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void showMenu(Admin admin) {
        System.out.println("\n----Admin Dashboard----");
        System.out.println("1) Manage Faculty Profiles");
        System.out.println("2) Manage Subject");
        System.out.println("3) Assign Subject to Faculty");
        System.out.println("4) View Faculty Teaching Load");
        System.out.println("5) Update Profile");
        System.out.println("0) Logout");

        System.out.print("Enter you choice: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println();
                manageFacultyProfiles(admin);
                break;
            case "2":
                System.out.println();
                manageSubject(admin);
                break;
            case "3":
                System.out.println();
                assignSubjectToFaculty(admin);
                break;
            case "4":
                System.out.println();
                viewFacultyTeachingLoad();
                break;
            case "5":
                System.out.println();
                updateProfile(admin);
                break;
            case "0":
                System.out.println();
                logOut();
            default:
                System.out.println("Invalid choice please try again!");
                break;
        }
    }

    private void manageFacultyProfiles(Admin admin) {
        appController.goToFacultyManagement(admin);
    }

    private void manageSubject(Admin admin) {
        appController.goToSubjectManagement(admin);
    }

    private void assignSubjectToFaculty(Admin admin) {
        appController.goToFacultyManagement(admin);
    }

    private void viewFacultyTeachingLoad() {

    }

    public void updateProfile(Admin admin) {
        System.out.println("\n== Update Profile ==");

        // Get current user
        int id = admin.getId();
        String currentUsername = admin.getUsername();
        String currentPassword = admin.getPassword();
        String currentFirstName = admin.getFirstName();
        String currentLastName = admin.getLastName();

        // Prompt for new values (press ENTER to keep current)
        System.out.print("Enter new username [Press ENTER to keep '" + currentUsername + "']: ");
        String usernameInput = scanner.nextLine().trim();
        String username = usernameInput.isEmpty() ? currentUsername : usernameInput;

        System.out.print("Enter new password [Press ENTER to keep current password]: ");
        String passwordInput = scanner.nextLine().trim();
        String password = passwordInput.isEmpty() ? currentPassword : passwordInput;

        System.out.print("Enter new first name [Press ENTER to keep '" + currentFirstName + "']: ");
        String firstNameInput = scanner.nextLine().trim();
        String firstName = firstNameInput.isEmpty() ? currentFirstName : firstNameInput;

        System.out.print("Enter new last name [Press ENTER to keep '" + currentLastName + "']: ");
        String lastNameInput = scanner.nextLine().trim();
        String lastName = lastNameInput.isEmpty() ? currentLastName : lastNameInput;

        boolean success = adminController.updateUser(id, username, password, firstName, lastName);

        if (success) {
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("Failed to update profile.");
        }

        appController.goToAdminDashboard(admin);
    }

    private void logOut() {
        appController.goToMainMenu();
    }

}
