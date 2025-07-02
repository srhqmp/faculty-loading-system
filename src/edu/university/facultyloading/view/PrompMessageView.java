package edu.university.facultyloading.view;

import edu.university.facultyloading.util.OutputFormatter;

public class PrompMessageView {

    public static void errorMessage(String message) {
        final String RED_BOLD = "\033[1;31m";
        final String RESET = "\033[0m";

        System.out.println(OutputFormatter.centerString(RED_BOLD +         message       + RESET));

    }

    public static void successMessage(String message) {

        final String GREEN_BOLD = "\033[1;32m";
        String RESET = "\033[0m";

        System.out.println(OutputFormatter.centerString(GREEN_BOLD +         message      + RESET));

    }

    public static void warningMessage(String message) {

        final String ORANGE_BOLD = "\033[1;38;5;208m"; // Bold + Orange-ish (ANSI 256 color)
        final String RESET = "\033[0m";

        System.out.println(OutputFormatter.centerString(ORANGE_BOLD +         message       + RESET));

    }
}
