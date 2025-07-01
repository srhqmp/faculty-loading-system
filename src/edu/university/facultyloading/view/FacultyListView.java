package edu.university.facultyloading.view;
import edu.university.facultyloading.model.Faculty;

import java.util.List;

public class FacultyListView {

    public void showFaculties(List<Faculty> faculties) {
        System.out.println("\n--- Faculty List ---");
        System.out.printf("%-5s %-15s %-15s %-20s %-10s %-5s\n",
                "ID", "First Name", "Last Name", "Major", "Available", "Score");

        for (Faculty f : faculties) {
            System.out.printf("%-5d %-15s %-15s %-20s %-10s %-5.2f\n",
                    f.getFacultyId(),
                    f.getFirstName(),
                    f.getLastName(),
                    f.getMajor(),
                    f.isAvailable() ? "Yes" : "No",
                    f.getStudentFeedbackScore());
        }
    }
}
