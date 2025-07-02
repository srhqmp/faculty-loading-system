package edu.university.facultyloading.view;

import edu.university.facultyloading.util.OutputFormatter;

public class PrompMessageView {

    public static void errorMessage(String message) {
    final String RED_BOLD = "\033[1;31m";
    final String RESET = "\033[0m";

    String centered = OutputFormatter.centerString(message.trim());
    System.out.println(RED_BOLD + centered + RESET);
}

public static void successMessage(String message) {
    final String GREEN_BOLD = "\033[1;32m";
    final String RESET = "\033[0m";

    String centered = OutputFormatter.centerString(message.trim());
    System.out.println(GREEN_BOLD + centered + RESET);
}

public static void warningMessage(String message) {
    final String ORANGE_BOLD = "\033[1;38;5;208m"; // ANSI 256-color orange
    final String RESET = "\033[0m";

    String centered = OutputFormatter.centerString(message.trim());
    System.out.println(ORANGE_BOLD + centered + RESET);
}
}
