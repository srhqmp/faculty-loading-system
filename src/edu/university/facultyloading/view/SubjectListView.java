package edu.university.facultyloading.view;

import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.util.OutputFormatter;

import java.util.List;

public class SubjectListView {
    public void showSubjects(List<Subject> subjects) {
        System.out.println();
        System.out.println(OutputFormatter.centerString("╔════════════════════════════════════╗"));
        System.out.println(OutputFormatter.centerString("║               SUBJECTS             ║"));
        System.out.println(OutputFormatter.centerString("╚════════════════════════════════════╝"));
        System.out.println();

        OutputFormatter.printDivider();
        System.out.printf("%-5s %-20s %-30s %-20s %-10s%n",
                "ID", "Name", "Description", "Recommended Major", "Complexity");
        OutputFormatter.printDivider();

        for (Subject s : subjects) {
            System.out.printf("%-5d %-20s %-30s %-20s %-10d%n",
                    s.getSubjectId(),
                    OutputFormatter.truncate(s.getName(), 20),
                    OutputFormatter.truncate(s.getDescription(), 30),
                    OutputFormatter.truncate(s.getRecommendedMajor(), 20),
                    s.getComplexityLevel());
        }
        OutputFormatter.printDivider();
    }
}
