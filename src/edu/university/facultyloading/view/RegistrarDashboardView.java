/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.university.facultyloading.view;

import java.util.List;
import java.util.Scanner;

import edu.university.facultyloading.util.OutputFormatter;

/**
 *
 * @author user
 */
public class RegistrarDashboardView {

    private final Scanner scanner;

    public RegistrarDashboardView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        System.out.println("\n---- Registrar Dashboard----");
        System.out.println("1) View Faculty Load Report");
        System.out.println("2) View Teaching Load");
        System.out.println("3) Approved Teaching Load");
        System.out.println("4) Logout");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println();
                viewFacultyLoadReport();
            case "2":
                System.out.println();
                viewTeachingLoad();
            case "3":
                System.out.println();
                approvedTeachingLoad();
            case "4":
                System.out.println();
                logOut();
                break;
            default:
                System.out.println();
                System.out.println("Invalid choice please try again.");
                break;
        }
    }

    private void viewFacultyLoadReport() {


        OutputFormatter.printHeader("Faculty Load Report");
        // table headers with fixed width
        System.out.printf("%-5s %-25s %-35s%n", "ID", "Name", "Description");
        OutputFormatter.printDivider();
    }

    private void viewTeachingLoad() {

    }

    private void approvedTeachingLoad() {

    }

    private void logOut() {
        System.out.println("You have been log out. Thank you!");
    }



}
