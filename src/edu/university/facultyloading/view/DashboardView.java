package edu.university.facultyloading.view;

import java.util.Scanner;

import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.util.PromptMessageView;
import edu.university.facultyloading.util.ScannerHelper;

public class DashboardView {
    private Scanner scanner;

    public DashboardView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int showAdminDashboard() {
        System.out.println();
        System.out.println();
        PromptMessageView.choices("╔════════════════════════════════════════════╗");
        PromptMessageView.choices("║               ADMIN DASHBOARD              ║");
        PromptMessageView.choices("╠════════════════════════════════════════════╣");
        PromptMessageView.choices("║ 1. View All Faculties                      ║");
        PromptMessageView.choices("║ 2. Manage Subjects                         ║");
        PromptMessageView.choices("║ 3. Assign Subjects to Faculty              ║");
        PromptMessageView.choices("║ 4. Assign the Best Faculty to a Subject    ║");
        PromptMessageView.choices("║ 5. Remove Assigned Subjects from Faculty   ║");
        PromptMessageView.choices("║ 6. View Faculty Loads                      ║");
        PromptMessageView.choices("║ 0. Logout                                  ║");
        PromptMessageView.choices("╚════════════════════════════════════════════╝");

        System.out.print(OutputFormatter.centerString("Select an option: "));

        return ScannerHelper.readInt(scanner);
    }

    public int showFacultyDashboard() {
        System.out.println();
        System.out.println();
        PromptMessageView.choices("╔════════════════════════════════════╗");
        PromptMessageView.choices("║          FACULTY DASHBOARD         ║");
        PromptMessageView.choices("╠════════════════════════════════════╣");
        PromptMessageView.choices("║ 1. View Assigned Subjects          ║");
        PromptMessageView.choices("║ 2. Update Availability             ║");
        PromptMessageView.choices("║ 0. Logout                          ║");
        PromptMessageView.choices("╚════════════════════════════════════╝");

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
            PromptMessageView.errorMessage("Invalid input. Please enter 'yes' or 'no'.");
            return promptAvailabilityUpdate();
        }
    }

}
