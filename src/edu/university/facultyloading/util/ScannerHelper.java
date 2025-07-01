package edu.university.facultyloading.util;

import java.util.Scanner;

public class ScannerHelper {
    public static int readInt(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return -1;
        }
    }
}
