package edu.university.facultyloading.view;

import java.util.List;
import java.util.Scanner;
import edu.university.facultyloading.controller.LoadController;
import edu.university.facultyloading.model.Load;
import edu.university.facultyloading.util.OutputFormatter;

public class RegistrarDashboardView {

    private final Scanner scanner;
    private final LoadController loadController; 

    public RegistrarDashboardView(Scanner scanner, LoadController loadController) {
        this.scanner = scanner;
        this.loadController = loadController;
    }

    public void showMenu() {
        System.out.println("\n---- Registrar Dashboard----");
        System.out.println("1) View Faculty Load Report");
        System.out.println("2) View Teaching Load");
        System.out.println("3) Approved Teaching Load");
        System.out.println("4) Logout");

        System.out.print("Enter you choice: ");

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
