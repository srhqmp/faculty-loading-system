package edu.university.facultyloading.util;

public class PromptMessage {

    public static void errorMessage(String message) {
        String centered = OutputFormatter.centerString(message.trim());

        System.out.println();
        System.out.println(ConsoleColors.RED_BOLD + centered + ConsoleColors.RESET);
        System.out.println();
    }

    public static void successMessage(String message) {
        String centered = OutputFormatter.centerString(message.trim());
        System.out.println();
        System.out.println(ConsoleColors.GREEN_BOLD + centered + ConsoleColors.RESET);
        System.out.println();
    }

    public static void header(String message) {
        String centered = OutputFormatter.centerString(message.trim());
        System.out.println(ConsoleColors.GREEN_BOLD + centered + ConsoleColors.RESET);
    }

    public static void choices(String message) {
        String centered = OutputFormatter.centerString(message.trim());
        System.out.println(ConsoleColors.CYAN_BOLD + centered + ConsoleColors.RESET);
    }

    public static void warningMessage(String message) {
        String centered = OutputFormatter.centerString(message.trim());

        System.out.println();
        System.out.println(ConsoleColors.ORANGE_BOLD + centered + ConsoleColors.RESET);
        System.out.println();
    }
}
