package edu.university.facultyloading.view;

import java.util.Scanner;

import edu.university.facultyloading.controller.AppController;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.util.OutputFormatter;

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
        System.out.println(OutputFormatter.centerString("╔════════════════════════════════════╗"));
        System.out.println(OutputFormatter.centerString("║          FACULTY DASHBOARD         ║"));
        System.out.println(OutputFormatter.centerString("╠════════════════════════════════════╣"));
        System.out.println(OutputFormatter.centerString("║ 1. Manage Profile                  ║"));
        System.out.println(OutputFormatter.centerString("║ 2. Add Subject Preference          ║"));
        System.out.println(OutputFormatter.centerString("║ 3. View Teaching Load              ║"));
        System.out.println(OutputFormatter.centerString("║ 4. Logout                          ║"));
        System.out.println(OutputFormatter.centerString("╚════════════════════════════════════╝"));
        System.out.print(OutputFormatter.centerString("Enter your choice [1-4]: "));

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
                System.out.println(OutputFormatter.centerString("Invalid choice please try again"));
                break;
        }
    }

    private void displayProfile(Faculty faculty) {
        System.out.println();
        System.out.println(OutputFormatter.centerString("╔════════════════════════════════════════════════╗"));
        System.out.println(OutputFormatter.centerString("║                   MY PROFILE                   ║"));
        System.out.println(OutputFormatter.centerString("╚════════════════════════════════════════════════╝"));

        System.out.println("\t\t\t    ID: " + faculty.getId());
        System.out.println("\t\t\t    Faculty ID: " + faculty.getFacultyId());
        System.out.println("\t\t\t    Username: " + faculty.getUsername());
        System.out.println("\t\t\t    Name: " + faculty.getFirstName() + " " + faculty.getLastName());
        System.out.println("\t\t\t    Role: " + faculty.getRole());
        System.out.println("\t\t\t    Major: " + faculty.getMajor());
        System.out.println("\t\t\t    Years of Experience: " + faculty.getYearsOfExperience());
        System.out.println("\t\t\t    Feedback Score: " + faculty.getStudentFeedbackScore());
        System.out.println("\t\t\t    Available: " + (faculty.getIsAvailable() == 1 ? "Yes" : "No"));
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
        System.out.println(OutputFormatter.centerString("You have been log out. Thank you!"));
    }

}
