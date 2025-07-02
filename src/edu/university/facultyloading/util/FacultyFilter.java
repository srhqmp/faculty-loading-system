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

    public static String getFacultyInitials(List<Faculty> faculties) {
        if (faculties == null) {
            return "";
        }

        String result = "";
        for (int i = 0; i < faculties.size(); i++) {
            Faculty faculty = faculties.get(i);

            String firstName = faculty.getFirstName();
            String lastName = faculty.getLastName();

            result += ("" + Character.toUpperCase(firstName.charAt(0)) + Character.toUpperCase(lastName.charAt(0)));
            if (i < faculties.size() - 1) {
                result += ", ";
            }
        }

        return result;
    }

}