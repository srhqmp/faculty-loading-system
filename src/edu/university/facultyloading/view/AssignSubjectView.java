package edu.university.facultyloading.view;

import java.util.Scanner;

import edu.university.facultyloading.util.ScannerHelper;

public class AssignSubjectView {
    private final Scanner scanner;

    public AssignSubjectView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int promptFacultyId() {
        System.out.print("Enter Faculty ID: ");
        return ScannerHelper.readInt(scanner);
    }

    public int promptSubjectId() {
        System.out.print("Enter Subject ID to assign: ");
        return ScannerHelper.readInt(scanner);
    }

    public void showAssignmentSuccess() {
        System.out.println("Subject successfully assigned to faculty.");
    }

    public void showAssignmentFailed() {
        System.out.println("Failed to assign subject. Please try again.");
    }
}
