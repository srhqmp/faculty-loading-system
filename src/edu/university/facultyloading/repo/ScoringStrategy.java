/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Subject;

/**
 *
 * @author acezh
 */
public interface ScoringStrategy {

    double calculateScore(Faculty faculty, Subject subject);
}
