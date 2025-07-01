package edu.university.facultyloading.view;

import edu.university.facultyloading.controller.FacultyController;
import edu.university.facultyloading.controller.AppController;
import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.util.OutputFormatter;

import java.util.List;
import java.util.Scanner;

public class FacultyManagementView {

    private final FacultyController facultyController;
    private final Scanner scanner;
    private AppController appController;

    public FacultyManagementView(Scanner scanner, FacultyController facultyController) {
        this.facultyController = facultyController;
        this.scanner = scanner;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void showMenu(Admin admin) {
        while (true) {
            System.out.println(OutputFormatter.centerString("╔══════════════════════════════════╗"));
            System.out.println(OutputFormatter.centerString("║       FACULTY MANAGEMENT         ║"));
            System.out.println(OutputFormatter.centerString("╠══════════════════════════════════╣"));
            System.out.println(OutputFormatter.centerString("║ 1. View All Faculties            ║"));
            System.out.println(OutputFormatter.centerString("║ 2. Edit Faculty                  ║"));
            System.out.println(OutputFormatter.centerString("║ 3. Delete Faculty                ║"));
            System.out.println(OutputFormatter.centerString("║ 0. Back                          ║"));
            System.out.println(OutputFormatter.centerString("╚══════════════════════════════════╝"));

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println();
                    viewAllFaculty();
                    break;
                case "2":
                    System.out.println();
                    updateFaculty();
                    break;
                case "3":
                    System.out.println();
                    deleteFaculty();
                    break;
                case "0":
                    appController.goToAdminDashboard(admin);
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void viewAllFaculty() {
        List<Faculty> facultyList = facultyController.getAllFaculties();

        OutputFormatter.printHeader("Faculty List");
        System.out.printf("%-5s %-25s %-35s %-35s %-35s %-5s %-15s %-12s%n",
                "ID", "Username", "First Name", "Last Name", "Major", "Yrs", "Score", "Available");
        OutputFormatter.printDivider();

        for (Faculty f : facultyList) {
            System.out.printf("%-5s %-25s %-35s %-35s %-35s %-5s %-15s %-12s%n",
                    f.getFacultyId(),
                    f.getUsername(),
                    f.getFirstName(),
                    f.getLastName(),
                    f.getMajor(),
                    f.getYearsOfExperience(),
                    f.getStudentFeedbackScore(),
                    f.getIsAvailable() == 1 ? "Yes" : "No");
        }

        OutputFormatter.printDivider();
    }

    private void updateFaculty() {
        viewAllFaculty();
        System.out.print("Enter faculty ID to update: ");
        int id = readInt();
        Faculty existing = facultyController.getFaculty(id);

        if (existing == null) {
            System.out.println("Faculty not found.");
            return;
        }

        System.out.print("Enter new major [ENTER to skip]: ");
        String major = scanner.nextLine().trim();
        if (major.isEmpty())
            major = existing.getMajor();

        System.out.print("Enter new years of experience [ENTER to skip]: ");
        String yearsInput = scanner.nextLine().trim();
        int years = yearsInput.isEmpty() ? existing.getYearsOfExperience() : Integer.parseInt(yearsInput);

        System.out.print("Enter new feedback score [ENTER to skip]: ");
        String scoreInput = scanner.nextLine().trim();
        double score = scoreInput.isEmpty() ? existing.getStudentFeedbackScore() : Double.parseDouble(scoreInput);

        System.out.print("Is available? (1 for Yes, 0 for No) [ENTER to skip]: ");
        String availInput = scanner.nextLine().trim();
        int isAvailable = availInput.isEmpty() ? existing.getIsAvailable() : Integer.parseInt(availInput);

        boolean success = facultyController.updateFaculty(id, major, years, score, isAvailable);

        if (success) {
            System.out.println("Faculty updated successfully.");
        } else {
            System.out.println("Failed to update faculty.");
        }
    }
 
    private void deleteFaculty() {
        viewAllFaculty();

        System.out.print("Enter faculty ID to delete: ");
        int id = readInt();

        Faculty existing = facultyController.getFaculty(id);
        if (existing == null) {
            System.out.println("Faculty not found.");
            return;
        }

        System.out.print("Are you sure you want to delete \"" + existing.getFirstName() + " " + existing.getLastName()
                + "\"? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (!confirm.equals("y"))
            return;

        if (facultyController.deleteFaculty(id)) {
            System.out.println("Faculty deleted successfully.");
        } else {
            System.out.println("Failed to delete faculty.");
        }
    }

    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return -1;
        }
    }
}
