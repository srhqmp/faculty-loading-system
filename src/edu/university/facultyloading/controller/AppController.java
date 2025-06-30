package edu.university.facultyloading.controller;

import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.view.AdminDashboardView;
import edu.university.facultyloading.view.FacultyDashboardView;
import edu.university.facultyloading.view.FacultyManagementView;
import edu.university.facultyloading.view.LoginView;
import edu.university.facultyloading.view.MainMenuView;
import edu.university.facultyloading.view.RegisterUserView;
import edu.university.facultyloading.view.RegistrarDashboardView;
import edu.university.facultyloading.view.SubjectManagementView;

public class AppController {
    private final MainMenuView mainMenuView;
    private final LoginView loginView;
    private final RegisterUserView registerUserView;
    // user dashboards
    private final AdminDashboardView adminDashboardView;
    private final FacultyDashboardView facultyDashboardView;
    private final RegistrarDashboardView registrarDashboardView;
    // management views
    private final SubjectManagementView subjectManagementView;
    private final FacultyManagementView facultyManagementView;

    public AppController(MainMenuView mainMenuView, LoginView loginView, RegisterUserView registerUserView,
            AdminDashboardView adminDashboardView, FacultyDashboardView facultyDashboardView,
            RegistrarDashboardView registrarDashboardView, SubjectManagementView subjectManagementView,
            FacultyManagementView facultyManagementView) {
        this.mainMenuView = mainMenuView;
        this.loginView = loginView;
        this.registerUserView = registerUserView;
        this.adminDashboardView = adminDashboardView;
        this.facultyDashboardView = facultyDashboardView;
        this.registrarDashboardView = registrarDashboardView;
        this.subjectManagementView = subjectManagementView;
        this.facultyManagementView = facultyManagementView;

        // set app controller to all views
        this.mainMenuView.setAppController(this);
        this.loginView.setAppController(this);
        this.registerUserView.setAppController(this);
        // dashboard
        this.adminDashboardView.setAppController(this);
        this.facultyDashboardView.setAppController(this);
        // manager
        this.facultyManagementView.setAppController(this);
        this.subjectManagementView.setAppController(this);
    }

    public void start() {
        mainMenuView.show();
    }

    public void goToMainMenu() {
        mainMenuView.show();
    }

    public void goToLogin() {
        loginView.showLoginPrompt();
    }

    public void gotToRegisterUser() {
        registerUserView.showRegisterPrompt();
    }

    public void goToAdminDashboard(Admin admin) {
        adminDashboardView.showMenu(admin);
    }

    public void goToFacultyDashboard(Faculty faculty) {
        facultyDashboardView.showMenu(faculty);
    }

    public void goToFacultyManagement(Admin admin) {
        facultyManagementView.showMenu(admin);
    }

    public void goToSubjectManagement(Admin admin) {
        subjectManagementView.showMenu(admin);
    }

}
