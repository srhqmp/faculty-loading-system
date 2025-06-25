package edu.university.facultyloading.main;

import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.repo.FacultyRepo;
import edu.university.facultyloading.repo_impl.FacultyRepoImpl;
import edu.university.facultyloading.util.DbConnection;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        DbConnection db = new DbConnection();
        
        FacultyRepo facultyRepo = new FacultyRepoImpl(db);
        // working
        // facultyRepo.createFaculty("klim_19", "password", "Karen", "Lim");
        // facultyRepo.deleteFaculty(6,3);

        // fetch faculties
        List<Faculty> faculties = facultyRepo.fetchFaculties();
        faculties.forEach(faculty -> System.out.println(faculty.getUsername()));
        // fetch faculty
//        Faculty faculty = facultyRepo.fetchFaculty(5);
//        System.out.println(faculty.getUsername());
        // update user profile
//        System.out.println(facultyRepo.updateFacultyProfile(5, "IT", 10, 75, 1));
//        System.out.println(facultyRepo.updateUserProfile(7, "kimposarah", "password", "Sarah Jane", "Quimpo"));
    
//        System.out.println(facultyRepo.restoreFaculty(7));
    }
    
}
