package edu.university.facultyloading.view;

import java.util.List;
import java.util.Scanner;

import edu.university.facultyloading.controller.SubjectController;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.util.ScannerHelper;

public class SubjectManagementView {
    private final SubjectController subjectController;
    private final Scanner scanner;

    public SubjectManagementView(Scanner scanner, SubjectController subjectController) {
        this.subjectController = subjectController;
        this.scanner = scanner;
    }

    public void showSubjectManagementMenu() {
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println(OutputFormatter.centerString("╔══════════════════════════════════╗"));
            System.out.println(OutputFormatter.centerString("║       SUBJECT MANAGEMENT         ║"));
            System.out.println(OutputFormatter.centerString("╠══════════════════════════════════╣"));
            System.out.println(OutputFormatter.centerString("║ 1. View All Subjects             ║"));
            System.out.println(OutputFormatter.centerString("║ 2. Add New Subject               ║"));
            System.out.println(OutputFormatter.centerString("║ 3. Edit Subject                  ║"));
            System.out.println(OutputFormatter.centerString("║ 4. Delete Subject                ║"));
            System.out.println(OutputFormatter.centerString("║ 0. Back to Admin Dashboard       ║"));
            System.out.println(OutputFormatter.centerString("╚══════════════════════════════════╝"));

            System.out.print(OutputFormatter.centerString("Enter choice: "));
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllSubjects();
                    break;
                case 2:
                    addSubject();
                    break;
                case 3:
                    editSubject();
                    break;
                case 4:
                    deleteSubject();
                    break;
                case 0:
                    return;
                default:
                    System.out.println(OutputFormatter.centerString("Invalid option. Please try again."));
            }
        }
    }

    private void viewAllSubjects() {
        List<Subject> subjects = subjectController.getAllSubjects();
        // Call overloaded viewAllSubjects
        viewAllSubjects(subjects);
    }

    public void viewAllSubjects(List<Subject> subjects) {
        System.out.println();
        System.out.println(OutputFormatter.centerString("╔════════════════════════════════════╗"));
        System.out.println(OutputFormatter.centerString("║               SUBJECTS             ║"));
        System.out.println(OutputFormatter.centerString("╚════════════════════════════════════╝"));
        System.out.println();

        OutputFormatter.printDivider();
        System.out.printf("%-5s %-20s %-30s %-20s %-10s%n",
                "ID", "Name", "Description", "Recommended Major", "Complexity");
        OutputFormatter.printDivider();

        for (Subject s : subjects) {
            System.out.printf("%-5d %-20s %-30s %-20s %-10d%n",
                    s.getSubjectId(),
                    OutputFormatter.truncate(s.getName(), 20),
                    OutputFormatter.truncate(s.getDescription(), 30),
                    OutputFormatter.truncate(s.getRecommendedMajor(), 20),
                    s.getComplexityLevel());
        }
        OutputFormatter.printDivider();
    }

    private void addSubject() {
        System.out.print("Enter subject name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter recommended major: ");
        String recommendedMajor = scanner.nextLine();

        System.out.print("Enter complexity level (1–10): ");
        int complexityLevel = ScannerHelper.readInt(scanner);

        boolean success = subjectController.createSubject(name, description, recommendedMajor, complexityLevel);
        if (success) {
            System.out.println("Subject added successfully.");
        } else {
            System.out.println("Failed to add subject. Please check your input.");
        }
    }

    private void editSubject() {
        viewAllSubjects();
        System.out.print("Enter subject ID to edit: ");
        int id = ScannerHelper.readInt(scanner);

        Subject existing = subjectController.getSubject(id);
        if (existing == null) {
            System.out.println("Subject not found.");
            return;
        }

        System.out.print("Enter new name [Press ENTER to keep \"" + existing.getName() + "\"]: ");
        String name = scanner.nextLine();
        System.out.print("Enter new description [Press ENTER to keep current]: ");
        String description = scanner.nextLine();
        System.out.print(
                "Enter new recommended major [Press ENTER to keep \"" + existing.getRecommendedMajor() + "\"]: ");
        String recommendedMajor = scanner.nextLine();
        System.out.print("Enter new complexity level [Press ENTER to keep " + existing.getComplexityLevel() + "]: ");
        String complexityInput = scanner.nextLine();

        String finalName = name.trim().isEmpty() ? existing.getName() : name.trim();
        String finalDescription = description.trim().isEmpty() ? existing.getDescription() : description.trim();
        String finalRecommendedMajor = recommendedMajor.trim().isEmpty() ? existing.getRecommendedMajor()
                : recommendedMajor.trim();
        int finalComplexity = existing.getComplexityLevel();

        if (!complexityInput.trim().isEmpty()) {
            try {
                int parsed = Integer.parseInt(complexityInput);
                finalComplexity = parsed;
            } catch (NumberFormatException e) {
                System.out.println("Invalid complexity level input. Keeping previous value.");
            }
        }

        boolean success = subjectController.updateSubject(id, finalName, finalDescription, finalRecommendedMajor,
                finalComplexity);
        if (success) {
            System.out.println("Subject updated successfully.");
        } else {
            System.out.println("Failed to update subject. Please check your input.");
        }
    }

    private void deleteSubject() {
        viewAllSubjects();
        System.out.print("Enter subject ID to delete: ");
        int id = ScannerHelper.readInt(scanner);

        Subject existing = subjectController.getSubject(id);
        if (existing == null) {
            System.out.println("Subject not found.");
            return;
        }

        System.out.print("Are you sure you want to delete \"" + existing.getName() + "\"? (y/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            boolean success = subjectController.deleteSubject(id);
            if (success) {
                System.out.println("Subject deleted.");
            } else {
                System.out.println("Failed to delete subject.");
            }
        } else {
            System.out.println("Delete cancelled.");
        }
    }

}
