package edu.university.facultyloading.view;

import java.util.Scanner;

import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.util.PromptMessageView;

public class LoginView {
    private Scanner scanner;

    public LoginView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String[] showLoginPrompt() {
        System.out.println();
        PromptMessageView.choices("╔════════════════════════════════════╗");
        PromptMessageView.choices("║               LOG IN               ║");
        PromptMessageView.choices("╚════════════════════════════════════╝");
        System.out.println();

        System.out.print(OutputFormatter.INDENT + "Username: ");
        String username = scanner.nextLine().trim();
        System.out.print(OutputFormatter.INDENT + "Password: ");
        String password = scanner.nextLine().trim();

        return new String[] { username, password };
    }

    public void showLoginFailed() {
        PromptMessageView.errorMessage("Login failed. Invalid username or password.");
    }
}
