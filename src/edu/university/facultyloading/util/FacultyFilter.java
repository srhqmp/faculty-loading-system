package edu.university.facultyloading.util;

import java.util.ArrayList;
import java.util.List;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Subject;

public class FacultyFilter {
    public static List<Faculty> filterBySubject(List<Faculty> facultyList, Subject subject) {
        List<Faculty> filtered = new ArrayList<>();

        for (Faculty faculty : facultyList) {
            if (!faculty.isAvailable())
                continue;

            if (!faculty.getMajor().equalsIgnoreCase(subject.getRecommendedMajor()))
                continue;

            filtered.add(faculty);
        }

        return filtered;
    }

    public static List<Faculty> availableOnly(List<Faculty> facultyList) {
        List<Faculty> available = new ArrayList<>();

        for (Faculty faculty : facultyList) {
            if (faculty.isAvailable()) {
                available.add(faculty);
            }
        }

        return available;
    }

}
