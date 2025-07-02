package edu.university.facultyloading.view;

import java.util.List;
import java.util.Scanner;

import edu.university.facultyloading.controller.SubjectController;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.util.FacultyFilter;
import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.util.PromptMessage;
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
            PromptMessage.choices("╔══════════════════════════════════╗");
            PromptMessage.choices("║       SUBJECT MANAGEMENT         ║");
            PromptMessage.choices("╠══════════════════════════════════╣");
            PromptMessage.choices("║ 1. View All Subjects             ║");
            PromptMessage.choices("║ 2. Add New Subject               ║");
            PromptMessage.choices("║ 3. Edit Subject                  ║");
            PromptMessage.choices("║ 4. Delete Subject                ║");
            PromptMessage.choices("║ 0. Back to Admin Dashboard       ║");
            PromptMessage.choices("╚══════════════════════════════════╝");

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
                    PromptMessage.errorMessage("Invalid option. Please try again.");
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
        PromptMessage.choices("╔════════════════════════════════════╗");
        PromptMessage.choices("║               SUBJECTS             ║");
        PromptMessage.choices("╚════════════════════════════════════╝");
        System.out.println();

        OutputFormatter.printDivider();
        System.out.printf("%-5s %-20s %-30s %-20s %-15s %-40s%n",
                "ID", "Name", "Description", "Recommended Major", "Complexity", "Instructors");
        OutputFormatter.printDivider();

        for (Subject s : subjects) {
            System.out.printf("%-5d %-20s %-30s %-20s %-15d %-40s%n",
                    s.getSubjectId(),
                    OutputFormatter.truncate(s.getName(), 20),
                    OutputFormatter.truncate(s.getDescription(), 30),
                    OutputFormatter.truncate(s.getRecommendedMajor(), 20),
                    s.getComplexityLevel(),
                    FacultyFilter.getFacultyInitials(s.getAssignedFaculties()));
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
            PromptMessage.successMessage("Subject added successfully.");
        } else {
            PromptMessage.errorMessage("Failed to add subject. Please check your input.");
        }
    }

    private void editSubject() {
        viewAllSubjects();
        System.out.print("Enter subject ID to edit: ");
        int id = ScannerHelper.readInt(scanner);

        Subject existing = subjectController.getSubject(id);
        if (existing == null) {
            PromptMessage.errorMessage("Subject not found.");
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
                PromptMessage.errorMessage("Invalid complexity level input. Keeping previous value.");
            }
        }

        boolean success = subjectController.updateSubject(id, finalName, finalDescription, finalRecommendedMajor,
                finalComplexity);
        if (success) {
            PromptMessage.successMessage("Subject updated successfully.");
        } else {
            PromptMessage.errorMessage("Failed to update subject. Please check your input.");
        }
    }

    private void deleteSubject() {
        viewAllSubjects();
        System.out.print("Enter subject ID to delete: ");
        int id = ScannerHelper.readInt(scanner);

        Subject existing = subjectController.getSubject(id);
        if (existing == null) {
            PromptMessage.errorMessage("Subject not found.");
            return;
        }

        System.out.print("Are you sure you want to delete \"" + existing.getName() + "\"? (y/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            boolean success = subjectController.deleteSubject(id);
            if (success) {
                PromptMessage.successMessage("Subject deleted successfully.");
            } else {
                PromptMessage.errorMessage("Failed to delete subject.");
            }
        } else {
            PromptMessage.successMessage("Delete cancelled.");
        }
    }

}
