package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Faculty;
import java.util.List;

public interface FacultyRepo {

    public Faculty fetchFaculty(int id);

    public Faculty fetchFaculty(String username, String password);

    public List<Faculty> fetchFaculties();

    public boolean createFaculty(String username, String password, String firstName, String lastName);

    public boolean isUsernameUnique(String username);

    public boolean updateUserProfile(int id, String username, String password, String firstName, String lastName);

    public boolean updateFacultyProfile(int facultyId, String major,
            int yearsOfExperience, double studentFeedbackScore, int isAvailable);

    public boolean archiveFaculty(int id);

    public boolean restoreFaculty(int id);

    public boolean deleteFaculty(int id, int facultyId);
}
