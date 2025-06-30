package edu.university.facultyloading.view;

import java.util.Scanner;

import edu.university.facultyloading.controller.AppController;
import edu.university.facultyloading.model.Faculty;

public class FacultyDashboardView {

    private final Scanner scanner;
    private AppController appController;

    public FacultyDashboardView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void showMenu(Faculty faculty) {
        System.out.println("\n-----Faculty Dashboard------");
        System.out.println("1) Manage Profile");
        System.out.println("2) Add Subject Preference");
        System.out.println("3) View Teaching Load");
        System.out.println("4) Logout");

        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println();
                manageProfile(faculty);
                break;
            case "2":
                System.out.println();
                addSubjectPreference();
                break;
            case "3":
                System.out.println();
                viewTeachingLoad();
                break;
            case "4":
                System.out.println();
                logOut();
                break;
            default:
                System.out.println();
                System.out.println("Invalid choice please try again");
                break;
        }
    }

    private void displayProfile(Faculty faculty) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║                   MY PROFILE                   ║");
        System.out.println("╚════════════════════════════════════════════════╝");

        System.out.println("ID: " + faculty.getId());
        System.out.println("Faculty ID: " + faculty.getFacultyId());
        System.out.println("Username: " + faculty.getUsername());
        System.out.println("Name: " + faculty.getFirstName() + " " + faculty.getLastName());
        System.out.println("Role: " + faculty.getRole());
        System.out.println("Major: " + faculty.getMajor());
        System.out.println("Years of Experience: " + faculty.getYearsOfExperience());
        System.out.println("Feedback Score: " + faculty.getStudentFeedbackScore());
        System.out.println("Available: " + (faculty.getIsAvailable() == 1 ? "Yes" : "No"));
        System.out.println();

    }

    private void manageProfile(Faculty faculty) {
        displayProfile(faculty);
        
    }

    private void addSubjectPreference() {

    }

    private void viewTeachingLoad() {

    }

    private void logOut() {
        System.out.println("You have been log out. Thank you!");
    }

}
