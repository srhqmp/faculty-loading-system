package edu.university.facultyloading.main;

import edu.university.facultyloading.controller.AdminController;
import edu.university.facultyloading.controller.AppController;
import edu.university.facultyloading.controller.FacultyController;
import edu.university.facultyloading.controller.LoadController;
import edu.university.facultyloading.controller.SubjectController;
import edu.university.facultyloading.repo.AdminRepo;
import edu.university.facultyloading.repo.FacultyRepo;
import edu.university.facultyloading.repo.LoadRepo;
import edu.university.facultyloading.repo.LoadSubjectRepo;
import edu.university.facultyloading.repo.SubjectRepo;
import edu.university.facultyloading.repo_impl.AdminRepoImpl;
import edu.university.facultyloading.repo_impl.FacultyRepoImpl;
import edu.university.facultyloading.repo_impl.LoadRepoImpl;
import edu.university.facultyloading.repo_impl.LoadSubjectRepoImpl;
import edu.university.facultyloading.repo_impl.SubjectRepoImpl;
import edu.university.facultyloading.util.DbConnection;
import edu.university.facultyloading.view.AdminDashboardView;
import edu.university.facultyloading.view.FacultyDashboardView;
import edu.university.facultyloading.view.FacultyManagementView;
import edu.university.facultyloading.view.LoginView;
import edu.university.facultyloading.view.MainMenuView;
import edu.university.facultyloading.view.RegisterUserView;
import edu.university.facultyloading.view.SubjectManagementView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // utils
        DbConnection db = new DbConnection();

        // Repo / coordination/functions with DB/ contains SQL
        AdminRepo adminRepo = new AdminRepoImpl(db);
        FacultyRepo facultyRepo = new FacultyRepoImpl(db);
        SubjectRepo subjectRepo = new SubjectRepoImpl(db);
        LoadRepo loadRepo = new LoadRepoImpl(db);
        LoadSubjectRepo loadSubjectRepo = new LoadSubjectRepoImpl(db);

        // Controller / functions within the system /these always needs the REPO
        AdminController adminController = new AdminController(adminRepo);
        FacultyController facultyController = new FacultyController(facultyRepo);
        SubjectController subjectController = new SubjectController(subjectRepo);
        LoadController loadController = new LoadController(loadRepo, loadSubjectRepo, subjectRepo);

        // Views
        LoginView loginView = new LoginView(scanner, adminController, facultyController);
        RegisterUserView registerView = new RegisterUserView(scanner, adminController, facultyController);
        MainMenuView mainMenuView = new MainMenuView(scanner);
        AdminDashboardView adminDashboardView = new AdminDashboardView(scanner, adminController);
        FacultyDashboardView facultyDashboardView = new FacultyDashboardView(scanner);
        FacultyManagementView facultyManagementView = new FacultyManagementView(scanner, facultyController);
        SubjectManagementView subjectManagementView = new SubjectManagementView(scanner, subjectController);

        // Manage navigation
        AppController viewContoller = new AppController(mainMenuView, loginView, registerView, adminDashboardView,
                facultyDashboardView, subjectManagementView, facultyManagementView);

        // Start the app
        viewContoller.start();
        
        //adminDashboardView.showMenu(null);
        //registerView.showRegisterPrompt();

    }

}
