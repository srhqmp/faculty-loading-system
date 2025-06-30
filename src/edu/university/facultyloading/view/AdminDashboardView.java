/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.university.facultyloading.view;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class AdminDashboardView {

    private final Scanner scanner;

    public AdminDashboardView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        System.out.println("\n----Admin Dashboard----");
        System.out.println("1) Manage Faculty Profiles");
        System.out.println("2) Manage Subject");
        System.out.println("3) Assign Subject to Faculty");
        System.out.println("4) View Faculty Teaching Load");
        System.out.println("5) Update Profile)");
        System.out.println("6) Logout");

        System.out.print("Enter you choice: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println();
                manageFacultyProfiles();
                break;
            case "2":
                System.out.println();
                manageSubject();
                break;
            case "3":
                System.out.println();
                assignSubjectToFaculty();
                break;
            case "4":
                System.out.println();
                viewFacultyTeachingLoad();
                break;
            case "5":
                System.out.println();
                updateProfile();
                break;
            case "6":
                System.out.println();
                logOut();
            default:
                System.out.println("Invalid choice please try again!");
                break;
        }
    }

    private void manageFacultyProfiles() {

    }

    private void manageSubject() {

    }

    private void assignSubjectToFaculty() {

    }

    private void viewFacultyTeachingLoad() {

    }

    private void updateProfile() {

    }

    private void logOut(){
        System.out.println("You have been log out. Thank you!");
    }

}
