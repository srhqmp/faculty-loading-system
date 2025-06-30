/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.university.facultyloading.repo_impl;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.ScoringStrategy;

/**
 *
 * @author acezh
 */
public class DefaultScoringStrategy implements ScoringStrategy {

    @Override
    public double calculateScore(Faculty faculty, Subject subject) {
        double score = 0;

        if (faculty.getMajor().equalsIgnoreCase(subject.getRecommendedMajor())) {
            score += 10;
        }

        // Skipping trainings
        // for (String training : faculty.getTrainings()) {
        //     if (subject.getRequiredTraining().contains(training)) {
        //         score += 5;
        //     }
        // }

        if (faculty.getSubjectsTaught().contains(subject.getName())) {
            score += 7;
        }

        score += faculty.getStudentFeedbackScore();
        score += faculty.getYearsOfExperience();

        return score;
    }

}
