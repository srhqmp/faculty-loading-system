package edu.university.facultyloading.view;

import java.util.Scanner;

import edu.university.facultyloading.util.ScannerHelper;

public class DashboardView {
    private Scanner scanner;

    public DashboardView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int showAdminDashboard() {
        System.out.println("\n=== Admin Dashboard ===");
        System.out.println("1. View All Faculties");
        System.out.println("2. Manage Subjects");
        System.out.println("3. Assign Subjects to Faculty");
        System.out.println("4. Remove Assigned Subjects from Faculty");
        System.out.println("5. View Faculty Loads");
        System.out.println("0. Logout");
        System.out.print("Select an option: ");

        return ScannerHelper.readInt(scanner);
    }

    public int showFacultyDashboard() {
        System.out.println("\n=== Faculty Dashboard ===");
        System.out.println("1. View Assigned Subjects");
        System.out.println("2: Update Availability");
        System.out.println("0. Logout");
        System.out.print("Select an option: ");

        return ScannerHelper.readInt(scanner);
    }

    public boolean promptAvailabilityUpdate() {
        System.out.print("Are you available for teaching? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("yes") || input.equals("y")) {
            return true;
        } else if (input.equals("no") || input.equals("n")) {
            return false;
        } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            return promptAvailabilityUpdate();
        }
    }

}
