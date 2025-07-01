package edu.university.facultyloading.view;

import java.util.Scanner;

import edu.university.facultyloading.util.OutputFormatter;

public class LoginView {
    private Scanner scanner;

    public LoginView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String[] showLoginPrompt() {
        System.out.println();
        System.out.println(OutputFormatter.centerString("╔════════════════════════════════════╗"));
        System.out.println(OutputFormatter.centerString("║               LOG IN               ║"));
        System.out.println(OutputFormatter.centerString("╚════════════════════════════════════╝"));
        System.out.println();

        System.out.print("\t\t\t    Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("\t\t\t    Password: ");
        String password = scanner.nextLine().trim();

        return new String[] { username, password };
    }

    public void showLoginFailed() {
        System.out.println(OutputFormatter.centerString("Login failed: Invalid username or password."));
    }
}
