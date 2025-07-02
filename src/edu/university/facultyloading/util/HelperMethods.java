package edu.university.facultyloading.util;

import java.util.List;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Subject;

public class HelperMethods {
    public static boolean facultyInTheList(int id, List<Faculty> faculties) {
        for (Faculty faculty : faculties) {
            if (faculty.getFacultyId() == id) {
                return true;
            }
        }
        return false;
    }

    public static boolean subjectInTheList(int id, List<Subject> subjects) {
        for (Subject subject : subjects) {
            if (subject.getSubjectId() == id) {
                return true;
            }
        }
        return false;
    }
}
