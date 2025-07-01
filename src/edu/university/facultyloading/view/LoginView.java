package edu.university.facultyloading.view;

import java.util.Scanner;

public class LoginView {
    private Scanner scanner;

    public LoginView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String[] showLoginPrompt() {
        System.out.println("=== Faculty Loading System Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        return new String[] { username, password };
    }

    public void showLoginFailed() {
        System.out.println("Login failed: Invalid username or password.");
    }
}
