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

    public int promptSubjectIdToAssign() {
        System.out.print("Enter Subject ID to assign: ");
        return ScannerHelper.readInt(scanner);
    }

    public int promptSubjectIdToRemove() {
        System.out.print("Enter Subject ID to remove: ");
        return ScannerHelper.readInt(scanner);
    }

    public void showAssignmentSuccess() {
        System.out.println("Subject successfully assigned to faculty.");
    }

    public void showAssignmentFailed() {
        System.out.println("Failed to assign subject. Please try again.");
    }

    public void showRemoveAssignmentSuccess() {
        System.out.println("Subject assignment removed successfully.");
    }

    public void showRemoveAssignmentFailed() {
        System.out.println("Failed to remove subject assignment. Please try again.");
    }
}
