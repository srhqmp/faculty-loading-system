package edu.university.facultyloading.view;

import edu.university.facultyloading.controller.AppController;
import edu.university.facultyloading.controller.SubjectController;
import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.util.OutputFormatter;
import java.util.List;
import java.util.Scanner;

public class SubjectManagementView {

    private final SubjectController subjectController;
    private final Scanner scanner;
    private AppController appController;

    public SubjectManagementView(Scanner scanner, SubjectController subjectController) {
        this.subjectController = subjectController;
        this.scanner = scanner;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void showMenu(Admin admin) {
        while (true) {
            System.out.println("\n== Subject Management ==");
            System.out.println("1. View All Subjects");
            System.out.println("2. Add New Subject");
            System.out.println("3. Edit Subject");
            System.out.println("4. Delete Subject");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println();
                    viewAllSubjects();
                    break;
                case "2":
                    System.out.println();
                    addSubject();
                    break;
                case "3":
                    System.out.println();
                    updateSubject();
                    break;
                case "4":
                    System.out.println();
                    deleteSubject();
                    break;
                case "0":
                    appController.goToAdminDashboard(admin);
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void viewAllSubjects() {
        List<Subject> subjects = subjectController.getAllSubjects();

        OutputFormatter.printHeader("Subject List");
        // table headers with fixed width
        System.out.printf("%-5s %-25s %-35s%n", "ID", "Name", "Description");
        OutputFormatter.printDivider();

        for (Subject subject : subjects) {
            String description = subject.getDescription();

            System.out.printf("%-5d %-25s %-35s%n",
                    subject.getId(),
                    subject.getName(),
                    description);
        }

        OutputFormatter.printDivider();
    }

    private void addSubject() {
        System.out.print("Enter subject name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        if (subjectController.createSubject(name, description)) {
            System.out.println("Subject created successfully.");
        } else {
            System.out.println("Failed to create subject.");
        }
    }

    private void updateSubject() {
        viewAllSubjects();

        System.out.print("Enter subject ID to update: ");
        int id = readInt();

        // validate if subject exist
        Subject existing = subjectController.getSubject(id);
        if (existing == null) {
            System.out.println("Subject not found.");
            return;
        }

        // Show prompt to allow skipping
        System.out.print("Enter new name [Press ENTER to skip]: ");
        String nameInput = scanner.nextLine().trim();
        String newName = nameInput.isEmpty() ? existing.getName() : nameInput;

        System.out.print("Enter new description [Press ENTER to skip]: ");
        String descInput = scanner.nextLine().trim();
        String newDescription = descInput.isEmpty() ? existing.getDescription() : descInput;

        // Call the controller's update method
        if (subjectController.updateSubject(id, newName, newDescription)) {
            System.out.println("Subject updated successfully.");
        } else {
            System.out.println("Failed to update subject.");
        }
    }

    private void deleteSubject() {
        viewAllSubjects();

        System.out.print("Enter subject ID to delete: ");
        int id = readInt();

        // Validate if subject exists before deletion
        Subject existing = subjectController.getSubject(id);
        if (existing == null) {
            System.out.println("Subject not found.");
            return;
        }

        // Confirm deletion
        System.out.print("Are you sure you want to delete \"" + existing.getName() + "\"? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (!confirm.equals("y")) {
            return;
        }

        if (subjectController.deleteSubject(id)) {
            System.out.println("Subject deleted successfully.");
        } else {
            System.out.println("Failed to delete subject.");
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
