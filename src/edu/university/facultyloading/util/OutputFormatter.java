package edu.university.facultyloading.util;

public class OutputFormatter {
    public static final String INDENT = "\t\t\t";

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private static final int CONSOLE_WIDTH = 100;

    // Public method to center a string using default width
    public static String centerString(String str) {
        return centerString(str, CONSOLE_WIDTH);
    }

    // Core logic: center string in given width
    private static String centerString(String str, int width) {
        int padding = (width - str.length()) / 2;
        padding = Math.max(0, padding);
        return String.format("%" + padding + "s%s", "", str);
    }

    public static void printHeader(String title) {
        printDivider();
        System.out.println(OutputFormatter.centerString(title));
        printDivider();
    }

    public static void printDivider() {
        String dash = new String(new char[CONSOLE_WIDTH]).replace("\0", "-");
        System.out.println(dash);
    }

    public static String truncate(String text, int maxLength) {
        if (text == null)
            return "";
        return text.length() <= maxLength ? text : text.substring(0, maxLength - 3) + "...";
    }

}
