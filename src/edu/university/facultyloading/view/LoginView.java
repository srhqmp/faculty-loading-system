package edu.university.facultyloading.view;

import java.util.Scanner;

import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.util.PromptMessage;

public class LoginView {
    private Scanner scanner;

    public LoginView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String[] showLoginPrompt() {
        System.out.println();
        PromptMessage.choices("╔════════════════════════════════════╗");
        PromptMessage.choices("║               LOG IN               ║");
        PromptMessage.choices("╚════════════════════════════════════╝");
        System.out.println();

        System.out.print(OutputFormatter.INDENT + "Username: ");
        String username = scanner.nextLine().trim();
        System.out.print(OutputFormatter.INDENT + "Password: ");
        String password = scanner.nextLine().trim();

        return new String[] { username, password };
    }

    public void showLoginFailed() {
        PromptMessage.errorMessage("Login failed. Invalid username or password.");
    }
}
