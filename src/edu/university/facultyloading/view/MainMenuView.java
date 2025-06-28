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
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

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
}
