package edu.university.facultyloading.view;
import edu.university.facultyloading.model.Subject;

import java.util.List;

public class SubjectListView {
    public void showSubjects(List<Subject> subjects) {
        System.out.println("\n--- Subject List ---");
        System.out.printf("%-5s %-20s %-30s %-20s %-8s\n",
                "ID", "Name", "Description", "Recommended Major", "Complexity");

        for (Subject s : subjects) {
            String shortDesc = s.getDescription().length() > 25
                    ? s.getDescription().substring(0, 25) + "..."
                    : s.getDescription();

            System.out.printf("%-5d %-20s %-30s %-20s %-8d\n",
                    s.getSubjectId(),
                    s.getName(),
                    shortDesc,
                    s.getRecommendedMajor(),
                    s.getComplexityLevel());
        }
    }
}
