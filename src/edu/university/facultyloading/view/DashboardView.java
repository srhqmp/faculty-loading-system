package edu.university.facultyloading.view;

import java.util.Scanner;

import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.util.ScannerHelper;

public class DashboardView {
    private Scanner scanner;

    public DashboardView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int showAdminDashboard() {
        System.out.println();
        System.out.println();
        System.out.println(OutputFormatter.centerString("╔════════════════════════════════════════════╗"));
        System.out.println(OutputFormatter.centerString("║               ADMIN DASHBOARD              ║"));
        System.out.println(OutputFormatter.centerString("╠════════════════════════════════════════════╣"));
        System.out.println(OutputFormatter.centerString("║ 1. View All Faculties                      ║"));
        System.out.println(OutputFormatter.centerString("║ 2. Manage Subjects                         ║"));
        System.out.println(OutputFormatter.centerString("║ 3. Assign Subjects to Faculty              ║"));
        System.out.println(OutputFormatter.centerString("║ 4. Remove Assigned Subjects from Faculty   ║"));
        System.out.println(OutputFormatter.centerString("║ 5. View Faculty Loads                      ║"));
        System.out.println(OutputFormatter.centerString("║ 0. Logout                                  ║"));
        System.out.println(OutputFormatter.centerString("╚════════════════════════════════════════════╝"));

        System.out.print(OutputFormatter.centerString("Select an option: "));

        return ScannerHelper.readInt(scanner);
    }

    public int showFacultyDashboard() {
        System.out.println();
        System.out.println();
        System.out.println(OutputFormatter.centerString("╔════════════════════════════════════╗"));
        System.out.println(OutputFormatter.centerString("║          FACULTY DASHBOARD         ║"));
        System.out.println(OutputFormatter.centerString("╠════════════════════════════════════╣"));
        System.out.println(OutputFormatter.centerString("║ 1. View Assigned Subjects          ║"));
        System.out.println(OutputFormatter.centerString("║ 2. Update Availability             ║"));
        System.out.println(OutputFormatter.centerString("║ 0. Logout                          ║"));
        System.out.println(OutputFormatter.centerString("╚════════════════════════════════════╝"));

        System.out.print(OutputFormatter.centerString("Select an option: "));

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
