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
public class FacultyDashboardView {

    private final Scanner scanner;

    public FacultyDashboardView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        System.out.println("\n-----Faculty Dashboard------");
        System.out.println("1) Manage Profile");
        System.out.println("2) Add Subject Preference");
        System.out.println("3) View Teaching Load");
        System.out.println("4) Logout");

        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println();
                manageProfile();
                break;
            case "2":
                System.out.println();
                addSubjectPreference();
                break;
            case "3":
                System.out.println();
                viewTeachingLoad();
                break;
            case "4":
                System.out.println();
                logOut();
                break;
            default:
            System.out.println();
            System.out.println("Invalid choice please try again");
                break;
        }
    }

    private void manageProfile(){

    }

    private void addSubjectPreference(){

    }
    private void viewTeachingLoad(){

    }
    private void logOut(){
        System.out.println("You have been log out. Thank you!");
    }

}
