package edu.university.facultyloading.view;

import edu.university.facultyloading.util.OutputFormatter;
import java.util.Scanner;

public class MainMenuView {

    private final Scanner scanner;
    private final OutputFormatter outputFormatter;
    private final LoginView loginView;
    private final RegisterUserView registerUserView;

    public MainMenuView(Scanner scanner, OutputFormatter outputFormatter, LoginView loginView, RegisterUserView registerUserView) {
        this.scanner = scanner;
        this.outputFormatter = outputFormatter;
        this.loginView = loginView;
        this.registerUserView = registerUserView;
    }

    public void show() {
        System.out.println("\n=== Main Menu ===");
        String choice;

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextLine().trim();
            System.out.println();

            if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                break; // valid input, proceed
            } else {
                System.out.println("Invalid input. Please try again.\n");
            }
        }

        switch (choice) {
            case "1":
                outputFormatter.clearConsole();
                loginView.showLoginPrompt();
                break;
            case "2":
                outputFormatter.clearConsole();
                registerUserView.showRegisterPrompt();
                break;
            case "3":
                System.out.println("Exiting the program. Goodbye!");
                return;
            default:
                outputFormatter.clearConsole();
                System.out.println("Invalid choice. Please enter 1, 2, or 3.\n");
        }
    }
}
