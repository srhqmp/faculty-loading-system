package edu.university.facultyloading.view;

import edu.university.facultyloading.util.OutputFormatter;
import java.util.Scanner;

public class MainMenuView {

    private final Scanner scanner;
    private final LoginView loginView;
    private final RegisterUserView registerUserView;

    public MainMenuView(Scanner scanner, LoginView loginView, RegisterUserView registerUserView) {
        this.scanner = scanner;
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
                OutputFormatter.clearConsole();
                loginView.showLoginPrompt();
                break;
            case "2":
                OutputFormatter.clearConsole();
                registerUserView.showRegisterPrompt();
                break;
            case "3":
                System.out.println("Exiting the program. Goodbye!");
                return;
            default:
                OutputFormatter.clearConsole();
                System.out.println("Invalid choice. Please try again.\n");
        }
    }
}
