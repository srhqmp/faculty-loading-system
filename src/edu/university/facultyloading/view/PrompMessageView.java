package edu.university.facultyloading.view;

import edu.university.facultyloading.util.OutputFormatter;

public class PrompMessageView {

   public static void errorMessage(String message) {
    final String RED_BOLD = "\033[1;31m";   // Bold + Red
    final String RESET = "\033[0m";         // Reset to default

    System.out.println(OutputFormatter.centerString("╔════════════════════════════════════╗"));
    System.out.println(OutputFormatter.centerString("║" + RED_BOLD + "        Invalid input detected      " + RESET + "║"));
    System.out.println(OutputFormatter.centerString("╠════════════════════════════════════╣"));
}

    public static void successMessage(String message) {

    }

    public static void warningMessage(String message) {

    }
}
