package edu.university.facultyloading.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.university.facultyloading.model.Faculty;

public class FacultySorter {
    public static List<Faculty> sortByFeedbackScore(List<Faculty> facultyList) {
        List<Faculty> sorted = new ArrayList<>(facultyList); // Create a copy to preserve original
        Collections.sort(sorted, new Comparator<Faculty>() {
            @Override
            public int compare(Faculty f1, Faculty f2) {
                return Double.compare(f2.getStudentFeedbackScore(), f1.getStudentFeedbackScore()); // Descending
            }
        });
        return sorted;
    }

    public static List<Faculty> sortByExperience(List<Faculty> facultyList) {
        List<Faculty> sorted = new ArrayList<>(facultyList);
        Collections.sort(sorted, new Comparator<Faculty>() {
            @Override
            public int compare(Faculty f1, Faculty f2) {
                return Integer.compare(f2.getYearsOfExperience(), f1.getYearsOfExperience()); // Descending
            }
        });
        return sorted;
    }

    public static List<Faculty> sortByName(List<Faculty> facultyList) {
        List<Faculty> sorted = new ArrayList<>(facultyList);
        Collections.sort(sorted, new Comparator<Faculty>() {
            @Override
            public int compare(Faculty f1, Faculty f2) {
                return f1.getName().compareToIgnoreCase(f2.getName()); // Ascending A–Z
            }
        });
        return sorted;
    }

}
