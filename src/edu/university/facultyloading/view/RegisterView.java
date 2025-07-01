package edu.university.facultyloading.view;

import java.util.Scanner;

import edu.university.facultyloading.util.OutputFormatter;

public class RegisterView {
    private Scanner scanner;

    public RegisterView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String[] showRegisterPrompt() {
        System.out.println();
        System.out.println(OutputFormatter.centerString("╔════════════════════════════════════╗"));
        System.out.println(OutputFormatter.centerString("║           Register User            ║"));
        System.out.println(OutputFormatter.centerString("╚════════════════════════════════════╝"));
        System.out.println();

        String userType = "";
        while (true) {
            System.out.print("\t\t    Enter user type (1 = Admin, 0 = Faculty): ");
            userType = scanner.nextLine().trim();
            if (userType.equals("1") || userType.equals("0"))
                break;
            System.out.println(OutputFormatter.centerString("Invalid input. Please enter 1 or 0."));
        }

        System.out.print("\t\t\t    Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("\t\t\t    Password: ");
        String password = scanner.nextLine().trim();

        System.out.print("\t\t\t    First Name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("\t\t\t    Last Name: ");
        String lastName = scanner.nextLine().trim();

        String major = "";
        String yearsOfExperience = "";

        if (userType.equals("0")) {
            System.out.print("\t\t\t    Major: ");
            major = scanner.nextLine().trim();

            System.out.print("\t\t\t    Years of Experience: ");
            yearsOfExperience = scanner.nextLine().trim();
        }

        return new String[] {
                userType, username, password, firstName, lastName, major, yearsOfExperience
        };
    }

    public void showRegistrationSuccess() {
        System.out.println("Registration successful!");
    }

    public void showRegistrationFailed(String reason) {
        System.out.println("Registration failed: " + reason);
    }
}
