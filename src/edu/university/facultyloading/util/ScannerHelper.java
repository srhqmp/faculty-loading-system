package edu.university.facultyloading.util;

import java.util.Scanner;

public class ScannerHelper {
    public static int readInt(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            PromptMessage.errorMessage("Invalid number.");
            return -1;
        }
    }
}
