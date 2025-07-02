package edu.university.facultyloading.view;

import java.util.Scanner;

import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.util.PromptMessage;
import edu.university.facultyloading.util.ScannerHelper;

public class DashboardView {
    private Scanner scanner;

    public DashboardView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int showAdminDashboard() {
        System.out.println();
        System.out.println();
        PromptMessage.choices("╔════════════════════════════════════════════╗");
        PromptMessage.choices("║               ADMIN DASHBOARD              ║");
        PromptMessage.choices("╠════════════════════════════════════════════╣");
        PromptMessage.choices("║ 1. View All Faculties                      ║");
        PromptMessage.choices("║ 2. Manage Subjects                         ║");
        PromptMessage.choices("║ 3. Assign Subjects to Faculty              ║");
        PromptMessage.choices("║ 4. Assign the Best Faculty to a Subject    ║");
        PromptMessage.choices("║ 5. Remove Assigned Subjects from Faculty   ║");
        PromptMessage.choices("║ 6. View Faculty Loads                      ║");
        PromptMessage.choices("║ 0. Logout                                  ║");
        PromptMessage.choices("╚════════════════════════════════════════════╝");

        System.out.print(OutputFormatter.centerString("Select an option: "));

        return ScannerHelper.readInt(scanner);
    }

    public int showFacultyDashboard() {
        System.out.println();
        System.out.println();
        PromptMessage.choices("╔════════════════════════════════════╗");
        PromptMessage.choices("║          FACULTY DASHBOARD         ║");
        PromptMessage.choices("╠════════════════════════════════════╣");
        PromptMessage.choices("║ 1. View Assigned Subjects          ║");
        PromptMessage.choices("║ 2. Update Availability             ║");
        PromptMessage.choices("║ 0. Logout                          ║");
        PromptMessage.choices("╚════════════════════════════════════╝");

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
            PromptMessage.errorMessage("Invalid input. Please enter 'yes' or 'no'.");
            return promptAvailabilityUpdate();
        }
    }

}
