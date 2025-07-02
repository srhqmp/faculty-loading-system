package edu.university.facultyloading.view;

import java.util.List;
import java.util.Scanner;

import edu.university.facultyloading.controller.AppController;
import edu.university.facultyloading.controller.FacultyController;
import edu.university.facultyloading.controller.SubjectController;
import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.ScoringStrategy;
import edu.university.facultyloading.util.LoadAssignmentManager;
import edu.university.facultyloading.util.OutputFormatter;

public class LoadAssignmentView {
    private final FacultyController facultyController;
    private final Scanner scanner;
    private AppController appController;
    private ScoringStrategy scoringStrategy;
    private final SubjectController subjectController;
    private final LoadAssignmentManager assignmentManager;

    public LoadAssignmentView(Scanner scanner, FacultyController facultyController, SubjectController subjectController,
            ScoringStrategy scoringStrategy) {
        this.facultyController = facultyController;
        this.subjectController = subjectController;
        this.scanner = scanner;
        this.assignmentManager = new LoadAssignmentManager(facultyController.getAllFaculties(), scoringStrategy);
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void showMenu(Admin admin) {
        while (true) {
            System.out.println("\n== Subject Load Management ==");
            System.out.println("1. View All Subjects");
            System.out.println("2. Assign Subject to Faculty");
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
                    assignSubjectToFaculty();
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

    private void assignSubjectToFaculty() {
        System.out.print("Enter subject ID to assign: ");
        int subjectId = Integer.parseInt(scanner.nextLine());

        Subject subject = subjectController.getSubject(subjectId);
        if (subject == null) {
            System.out.println("Subject not found.");
            return;
        }

        assignmentManager.assignFaculty(subject);

    }

    private void assignSubjectToFacultyTEST() {
        System.out.print("Enter faculty ID to assign subject: ");
        int facultyId = Integer.parseInt(scanner.nextLine());

        Faculty faculty = facultyController.getFaculty(facultyId);
        if (faculty == null) {
            System.out.println("Faculty not found.");
            return;
        }

        System.out.print("Enter subject ID to assign: ");
        int subjectId = Integer.parseInt(scanner.nextLine());

        Subject subject = subjectController.getSubject(subjectId);
        if (subject == null) {
            System.out.println("Subject not found.");
            return;
        }

        if (subject != null) {
            assignmentManager.assignFaculty(subject);
            System.out.println(
                    "Subject assigned successfully to " + faculty.getFirstName() + " " + faculty.getLastName());
        } else {
            System.out.println("Failed to assign subject. Please try again.");
        }
    }
}
