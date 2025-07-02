package edu.university.facultyloading.view;

import java.util.Scanner;

import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.util.PromptMessageView;

public class RegisterView {
    private Scanner scanner;

    public RegisterView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String[] showRegisterPrompt() {
        System.out.println();
        PromptMessageView.choices("╔════════════════════════════════════╗");
        PromptMessageView.choices("║           Register User            ║");
        PromptMessageView.choices("╚════════════════════════════════════╝");
        System.out.println();

        String userType = "";
        while (true) {
            System.out.print(OutputFormatter.INDENT + "Enter user type (1 = Admin, 0 = Faculty): ");
            userType = scanner.nextLine().trim();
            if (userType.equals("1") || userType.equals("0"))
                break;
            PromptMessageView.errorMessage("Invalid input. Please enter 1 or 0.");
        }

        System.out.print(OutputFormatter.INDENT + "Username: ");
        String username = scanner.nextLine().trim();

        System.out.print(OutputFormatter.INDENT + "Password: ");
        String password = scanner.nextLine().trim();

        System.out.print(OutputFormatter.INDENT + "First Name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print(OutputFormatter.INDENT + "Last Name: ");
        String lastName = scanner.nextLine().trim();

        String major = "";
        String yearsOfExperience = "";

        if (userType.equals("0")) {
            System.out.print(OutputFormatter.INDENT + "Major: ");
            major = scanner.nextLine().trim();

            System.out.print(OutputFormatter.INDENT + "Years of Experience: ");
            yearsOfExperience = scanner.nextLine().trim();
        }

        return new String[] {
                userType, username, password, firstName, lastName, major, yearsOfExperience
        };
    }

    public void showRegistrationSuccess() {
        PromptMessageView.successMessage("Registration successful!");
    }

    public void showRegistrationFailed(String reason) {
        PromptMessageView.errorMessage("Registration failed: " + reason);
    }
}
