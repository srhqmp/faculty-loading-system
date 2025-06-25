/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Faculty;
import java.util.List;

/**
 *
 * @author user
 */
public interface FacultyRepo {

    public Faculty fetchFaculty(int id);

    public List<Faculty> fetchFaculties();

    public boolean createFaculty(String username, String password, String firstName, String lastName);

    public boolean updateUserProfile(int id, String username, String password, String firstName, String lastName);

    public boolean updateFacultyProfile(int facultyId, String major,
            int yearsOfExperience, double studentFeedbackScore, int isAvailable);

    public boolean archiveFaculty(int id);

    public boolean restoreFaculty(int id);

    public boolean deleteFaculty(int id, int facultyId);
}
