package edu.university.facultyloading.view;

import java.util.List;
import java.util.Scanner;

import edu.university.facultyloading.controller.SubjectController;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.util.OutputFormatter;

public class SubjectManagementView {
    private final SubjectController subjectController;
    private final Scanner scanner;

    public SubjectManagementView(Scanner scanner, SubjectController subjectController) {
        this.subjectController = subjectController;
        this.scanner = scanner;
    }

    public void showSubjectManagementMenu() {
        while (true) {
            System.out.println("\n=== Manage Subjects ===");
            System.out.println("1. View All Subjects");
            System.out.println("2. Add Subject");
            System.out.println("3. Edit Subject");
            System.out.println("4. Delete Subject");
            System.out.println("0. Back to Dashboard");
            System.out.print("Enter choice: ");
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
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewAllSubjects() {
        List<Subject> subjects = subjectController.getAllSubjects();
        System.out.printf("%-5s %-20s %-30s %-20s %-10s%n",
                "ID", "Name", "Description", "Recommended Major", "Complexity");
        for (Subject s : subjects) {
            System.out.printf("%-5d %-20s %-30s %-20s %-10d%n",
                    s.getSubjectId(),
                    OutputFormatter.truncate(s.getName(), 20),
                    OutputFormatter.truncate(s.getDescription(), 30),
                    OutputFormatter.truncate(s.getRecommendedMajor(), 20),
                    s.getComplexityLevel());
        }
    }

    private void addSubject() {
        System.out.print("Enter subject name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter recommended major: ");
        String recommendedMajor = scanner.nextLine();

        System.out.print("Enter complexity level (integer): ");
        int complexityLevel = scanner.nextInt();
        scanner.nextLine();

        Subject subject = new Subject();
        subject.setName(name);
        subject.setDescription(description);
        subject.setRecommendedMajor(recommendedMajor);
        subject.setComplexityLevel(complexityLevel);

        subjectController.createSubject(subject);
        System.out.println("Subject added.");
    }

    private void editSubject() {
        viewAllSubjects();
        System.out.print("Enter subject ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();

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

        if (!name.trim().isEmpty())
            existing.setName(name);
        if (!description.trim().isEmpty())
            existing.setDescription(description);
        if (!recommendedMajor.trim().isEmpty())
            existing.setRecommendedMajor(recommendedMajor);
        if (!complexityInput.trim().isEmpty()) {
            try {
                int complexityLevel = Integer.parseInt(complexityInput);
                existing.setComplexityLevel(complexityLevel);
            } catch (NumberFormatException e) {
                System.out.println("Invalid complexity level input. Keeping previous value.");
            }
        }

        subjectController.updateSubject(existing);
        System.out.println("Subject updated.");
    }

    private void deleteSubject() {
        viewAllSubjects();
        System.out.print("Enter subject ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Subject existing = subjectController.getSubject(id);
        if (existing == null) {
            System.out.println("Subject not found.");
            return;
        }

        System.out.print("Are you sure you want to delete \"" + existing.getName() + "\"? (y/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            subjectController.deleteSubject(id);
            System.out.println("Subject deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }
}
