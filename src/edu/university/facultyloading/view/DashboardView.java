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
        System.out.println("4. View Faculty Loads");
        System.out.println("5. Logout");
        System.out.print("Select an option: ");

        return ScannerHelper.readInt(scanner);
    }

    public int showFacultyDashboard() {
        System.out.println("\n=== Faculty Dashboard ===");
        System.out.println("1. View Assigned Subjects");
        System.out.println("2. Logout");
        System.out.print("Select an option: ");

        return ScannerHelper.readInt(scanner);
    }
}
