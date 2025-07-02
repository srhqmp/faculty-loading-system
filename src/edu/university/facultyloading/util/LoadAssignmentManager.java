/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.university.facultyloading.util;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.ScoringStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acezh
 */
public class LoadAssignmentManager {

    private final List<Faculty> facultyList;
    private final ScoringStrategy strategy;

    private final Map<Subject, Faculty> assignments;

    public LoadAssignmentManager(List<Faculty> facultyList, ScoringStrategy strategy) {

        this.facultyList = facultyList;
        this.strategy = strategy;
        this.assignments = new HashMap<>();
    }

    public void assignFaculty(Subject subject) {
        List<Faculty> qualified = FacultyFilter.filterBySubject(facultyList, subject);
        Faculty bestFaculty = null;
        double highestScore = 0;

        for (Faculty faculty : qualified) {
            double score = strategy.calculateScore(faculty, subject);
            if (score > highestScore) {
                highestScore = score;
                bestFaculty = faculty;
            }
        }

        if (bestFaculty != null) {
            assignments.put(subject, bestFaculty);
            System.out.println("✅ Assigned " + bestFaculty.getName() + " to " + subject.getName()
                    + " | Score: " + highestScore);
        } else {
            System.out.println("⚠️ No qualified faculty found for " + subject.getName());
        }
    }

    public void displayAssignments() {
        System.out.println("=== Faculty Assignments ===");
        if (assignments.isEmpty()) {
            System.out.println("No assignments yet.");
        } else {
            for (Map.Entry<Subject, Faculty> entry : assignments.entrySet()) {
                System.out.println(entry.getKey().getName() + " → " + entry.getValue().getName());
            }
        }
    }

    public void removeAssignment(Subject subject) {
        assignments.remove(subject);
    }

    public Map<Subject, Faculty> getAssignments() {
        return assignments;
    }
}
