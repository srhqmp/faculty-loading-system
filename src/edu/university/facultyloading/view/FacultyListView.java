package edu.university.facultyloading.view;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.util.OutputFormatter;

import java.util.List;

public class FacultyListView {

    public void showFaculties(List<Faculty> faculties) {
        System.out.println();
        System.out.println(OutputFormatter.centerString("╔════════════════════════════════════╗"));
        System.out.println(OutputFormatter.centerString("║             FACULTIES              ║"));
        System.out.println(OutputFormatter.centerString("╚════════════════════════════════════╝"));
        System.out.println();

        OutputFormatter.printDivider();
        System.out.printf("%-5s %-15s %-15s %-20s %-10s %-10s %-5s\n",
                "ID", "First Name", "Last Name", "Major", "Available", "Score", "Assigned Subjects");
        OutputFormatter.printDivider();

        for (Faculty f : faculties) {
            System.out.printf("%-5d %-15s %-15s %-20s %-10s %-10.2f %-5s\n",
                    f.getFacultyId(),
                    f.getFirstName(),
                    f.getLastName(),
                    f.getMajor(),
                    f.isAvailable() ? "Yes" : "No",
                    f.getStudentFeedbackScore(),
                    f.getAssignedSubjects().size());
        }

        OutputFormatter.printDivider();
    }
}
