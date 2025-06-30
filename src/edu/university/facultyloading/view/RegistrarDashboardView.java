/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.university.facultyloading.view;

import java.util.List;
import java.util.Scanner;
import edu.university.facultyloading.controller.LoadController;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Load;
import edu.university.facultyloading.util.OutputFormatter;

/**
 *
 * @author user
 */
public class RegistrarDashboardView {

    private final Scanner scanner;
    private final LoadController loadController;

    public RegistrarDashboardView(Scanner scanner, LoadController loadController) {
        this.scanner = scanner;
        this.loadController = loadController;
    }

    public void showMenu() {
        System.out.println(OutputFormatter.centerString("╔══════════════════════════════╗"));
        System.out.println(OutputFormatter.centerString("║     Registrar DashBahoard    ║"));
        System.out.println(OutputFormatter.centerString("╠══════════════════════════════╣"));
        System.out.println(OutputFormatter.centerString("║ 1. View Faculty Load Report  ║"));
        System.out.println(OutputFormatter.centerString("║ 2. View Teaching Load        ║"));
        System.out.println(OutputFormatter.centerString("║ 3. Approved Teaching Load    ║"));
        System.out.println(OutputFormatter.centerString("║ 0. Logout                    ║"));
        System.out.println(OutputFormatter.centerString("╚══════════════════════════════╝"));
        System.out.print(OutputFormatter.centerString("Enter choice [1-3]: "));
        System.out.println();

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println();
                viewFacultyLoadReport();
                break;
            case "2":
                System.out.println();
                viewTeachingLoad();
                break;
            case "3":
                System.out.println();
                approvedTeachingLoad();
                break;
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
        List<Load> loads = loadController.getAllLoads();

        OutputFormatter.printHeader("Faculty Load Report");
        // table headers with fixed width
        System.out.printf("%-5s %-25s %-35s%n", "ID", "Name", "Description");
        OutputFormatter.printDivider();

        for (Load load : loads) {
            System.out.printf("%-5d %-25s %-35s%n",
                    load.getId());
        }
    }

    private void viewTeachingLoad() {

    }

    private void approvedTeachingLoad() {

    }

    private void logOut() {
        System.out.println("You have been log out. Thank you!");
    }

}
