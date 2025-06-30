package edu.university.facultyloading.view;

import edu.university.facultyloading.util.ConsoleColors;
import edu.university.facultyloading.util.OutputFormatter;
import java.util.Scanner;
import edu.university.facultyloading.util.StringHelper;

public class MainMenuView {

    private final Scanner scanner;
    private final LoginView loginView;
    private final RegisterUserView registerUserView;
    // console width for centering
    private final int CONSOLE_WIDTH = 80;

    public MainMenuView(Scanner scanner, LoginView loginView, RegisterUserView registerUserView) {
        this.scanner = scanner;
        this.loginView = loginView;
        this.registerUserView = registerUserView;
    }

    public void show() {
        System.out.println();
        System.out.println(OutputFormatter.centerString("╔════════════════════════════════════╗"));
        System.out.println(OutputFormatter.centerString("║        FACULTY LOADING SYSTEM      ║"));
        System.out.println(OutputFormatter.centerString("╚════════════════════════════════════╝"));
        System.out.println();

        String choice;

        while (true) {
            System.out.println(OutputFormatter.centerString("╔══════════════════════════════╗"));
            System.out.println(OutputFormatter.centerString("║           MAIN MENU          ║"));
            System.out.println(OutputFormatter.centerString("╠══════════════════════════════╣"));
            System.out.println(OutputFormatter.centerString("║ 1. Login                     ║"));
            System.out.println(OutputFormatter.centerString("║ 2. Register                  ║"));
            System.out.println(OutputFormatter.centerString("║ 3. Exit                      ║"));
            System.out.println(OutputFormatter.centerString("╚══════════════════════════════╝"));
            System.out.print(OutputFormatter.centerString("Enter choice [1-3]: "));
            choice = scanner.nextLine().trim();
            System.out.println();

            if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                break;
            } else {
                System.out.println(OutputFormatter.centerString("⚠️  Invalid input. Please try again.\n"));
            }
        }


        switch (choice) {
            case "1":
                loginView.showLoginPrompt();
                break;
            case "2":
                registerUserView.showRegisterPrompt();
                break;
            case "3":
                System.out.println("👋 Exiting the program. Goodbye!");
                return;
            default:
                System.out.println("⚠️  Unexpected error occurred.");
        }
    }
}
