package edu.university.facultyloading.view;

import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.util.PromptMessage;
import edu.university.facultyloading.model.Faculty;

import java.util.List;

public class FacultyLoadView {
    public void showFacultyLoads(Faculty faculty, List<Subject> assignedSubjects) {
        System.out.println();
        PromptMessage.choices("╔════════════════════════════════════╗");
        PromptMessage.choices("║             FACULTY LOAD           ║");
        PromptMessage.choices("╚════════════════════════════════════╝");
        PromptMessage.header(faculty.getFullname());
        System.out.println();

        if (assignedSubjects.isEmpty()) {
            PromptMessage.warningMessage("No subjects assigned.");
            return;
        }

        if (!faculty.isAvailable()) {
            PromptMessage.warningMessage("Faculty is currently unavailable.");
        }

        OutputFormatter.printDivider();
        System.out.printf("%-5s %-20s %-30s\n", "ID", "Subject Name", "Description");
        OutputFormatter.printDivider();
        for (Subject s : assignedSubjects) {
            System.out.printf("%-5d %-20s %-30s\n", s.getSubjectId(), s.getName(),
                    OutputFormatter.truncate(s.getDescription(), 30));
        }
        OutputFormatter.printDivider();
    }
}
