package edu.university.facultyloading.main;

import edu.university.facultyloading.controller.MainController;
import edu.university.facultyloading.repo.AdminRepo;
import edu.university.facultyloading.repo.FacultyRepo;
import edu.university.facultyloading.repo.LoadRepo;
import edu.university.facultyloading.repo.ScoringStrategy;
import edu.university.facultyloading.repo.SubjectRepo;
import edu.university.facultyloading.repo_impl.AdminRepoImpl;
import edu.university.facultyloading.repo_impl.DefaultScoringStrategy;
import edu.university.facultyloading.repo_impl.FacultyRepoImpl;
import edu.university.facultyloading.repo_impl.LoadRepoImpl;
import edu.university.facultyloading.repo_impl.SubjectRepoImpl;
import edu.university.facultyloading.util.DbConnection;

public class Main {
    public static void main(String[] args) {
        // Setup db
        DbConnection dbConnection = new DbConnection();

        // Repo
        AdminRepo adminRepo = new AdminRepoImpl(dbConnection);
        FacultyRepo facultyRepo = new FacultyRepoImpl(dbConnection);
        SubjectRepo subjectRepo = new SubjectRepoImpl(dbConnection);
        LoadRepo loadRepo = new LoadRepoImpl(dbConnection);
        // Main Controller
        MainController mainController = new MainController(adminRepo, facultyRepo, subjectRepo, loadRepo);

        mainController.start();
    }
}
