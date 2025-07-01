package edu.university.facultyloading.view;

import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.model.Faculty;

import java.util.List;

public class FacultyLoadView {
    public void showFacultyLoads(Faculty faculty, List<Subject> assignedSubjects) {
        System.out.println("\n--- Load for Faculty: " + faculty.getFirstName() + " " + faculty.getLastName() + " ---");
        if (assignedSubjects.isEmpty()) {
            System.out.println("No subjects assigned.");
            return;
        }

        System.out.printf("%-5s %-20s %-30s\n", "ID", "Subject Name", "Description");
        for (Subject s : assignedSubjects) {
            String shortDesc = s.getDescription().length() > 30
                    ? s.getDescription().substring(0, 30) + "..."
                    : s.getDescription();

            System.out.printf("%-5d %-20s %-30s\n", s.getSubjectId(), s.getName(), shortDesc);
        }
    }
}
