package edu.university.facultyloading.controller;

import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.AdminRepo;
import edu.university.facultyloading.repo.FacultyRepo;
import edu.university.facultyloading.repo.LoadRepo;
import edu.university.facultyloading.repo.SubjectRepo;
import edu.university.facultyloading.view.*;

import java.util.List;
import java.util.Scanner;

public class MainController {

    private final AdminRepo adminRepo;
    private final FacultyRepo facultyRepo;
    private final SubjectRepo subjectRepo;
    private final LoadRepo loadRepo;
    private final Scanner scanner = new Scanner(System.in);

    private final SubjectController subjectController;

    private final LoginView loginView = new LoginView(scanner);
    private final DashboardView dashboardView = new DashboardView(scanner);
    private final FacultyListView facultyListView = new FacultyListView();
    private final SubjectListView subjectListView = new SubjectListView();
    private final AssignSubjectView assignSubjectView = new AssignSubjectView(scanner);
    private final FacultyLoadView facultyLoadView = new FacultyLoadView();
    private final RegisterView registerView = new RegisterView(scanner);

    private Admin loggedInAdmin = null;
    private Faculty loggedInFaculty = null;

    public MainController(AdminRepo adminRepo, FacultyRepo facultyRepo, SubjectRepo subjectRepo,
            LoadRepo loadRepo) {
        this.adminRepo = adminRepo;
        this.facultyRepo = facultyRepo;
        this.subjectRepo = subjectRepo;
        this.loadRepo = loadRepo;

        this.subjectController = new SubjectController(subjectRepo);
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Welcome to Faculty Loading System ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Choose an option: ");

            int choice = -1;

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    handleRegistration();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleLogin() {
        String[] credentials = loginView.showLoginPrompt();
        String username = credentials[0];
        String password = credentials[1];

        loggedInAdmin = adminRepo.authenticate(username, password);
        System.out.println();
        System.out.println();

        if (loggedInAdmin != null) {
            System.out.println("Welcome " + loggedInAdmin.getFullname() + "!");
            displayAdminDashboard();
            return;
        }

        loggedInFaculty = facultyRepo.authenticate(username, password);
        if (loggedInFaculty != null) {
            System.out.println("Welcome " + loggedInFaculty.getFullname() + "!");
            displayFacultyDashboard();
            return;
        }

        loginView.showLoginFailed();
    }

    private void handleRegistration() {
        String[] data = registerView.showRegisterPrompt();

        int userType = Integer.parseInt(data[0]);
        String username = data[1];
        String password = data[2];
        String firstName = data[3];
        String lastName = data[4];

        if (userType == 1) {
            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(password);
            admin.setFirstName(firstName);
            admin.setLastName(lastName);
            adminRepo.create(admin);

            System.out.println("Admin registered successfully.");
            loggedInAdmin = adminRepo.authenticate(username, password);
            if (loggedInAdmin != null) {
                displayAdminDashboard();
            }
        } else {
            String major = data[5];
            int yearsOfExperience = Integer.parseInt(data[6]);

            Faculty faculty = new Faculty();
            faculty.setUsername(username);
            faculty.setPassword(password);
            faculty.setFirstName(firstName);
            faculty.setLastName(lastName);
            faculty.setMajor(major);
            faculty.setYearsOfExperience(yearsOfExperience);
            facultyRepo.create(faculty);

            System.out.println("Faculty registered successfully.");
            loggedInFaculty = facultyRepo.authenticate(username, password);
            if (loggedInFaculty != null) {
                displayFacultyDashboard();
            }
        }
    }

    private void displayAdminDashboard() {
        boolean running = true;
        while (running) {
            int choice = dashboardView.showAdminDashboard();

            // Provide faculties and subjects
            List<Faculty> faculties = facultyRepo.getAll();
            for (Faculty faculty : faculties) {
                // get faculty load
                List<Subject> assignedSubjects = loadRepo.getSubjectsByFacultyId(faculty.getFacultyId());
                faculty.setAssignedSubjects(assignedSubjects);
            }
            List<Subject> subjects = subjectRepo.getAll();

            switch (choice) {
                case 1:
                    // View all Faculties
                    facultyListView.showFaculties(faculties);
                    break;
                case 2:
                    // Manage Subjects
                    SubjectManagementView subjectManagementView = new SubjectManagementView(scanner, subjectController);
                    subjectManagementView.showSubjectManagementMenu();
                    break;
                case 3:
                    // Assign Subjects to Faculty
                    facultyListView.showFaculties(faculties);
                    int facultyId = assignSubjectView.promptFacultyId();
                    subjectListView.showSubjects(subjects);
                    int subjectId = assignSubjectView.promptSubjectId();
                    boolean success = loadRepo.assignSubjectToFaculty(facultyId, subjectId);
                    if (success)
                        assignSubjectView.showAssignmentSuccess();
                    else
                        assignSubjectView.showAssignmentFailed();
                    break;
                case 4:
                    // View Faculty Loads
                    facultyListView.showFaculties(faculties);

                    int viewFacultyId = assignSubjectView.promptFacultyId();
                    Faculty faculty = facultyRepo.getById(viewFacultyId);

                    if (faculty != null) {
                        List<Subject> assignedSubjects = loadRepo.getSubjectsByFacultyId(viewFacultyId);
                        facultyLoadView.showFacultyLoads(faculty, assignedSubjects);
                    } else {
                        System.out.println("Faculty not found.");
                    }
                    break;
                case 0:
                    // Logout
                    loggedInAdmin = null;
                    running = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayFacultyDashboard() {
        boolean running = true;
        while (running) {
            int choice = dashboardView.showFacultyDashboard();
            switch (choice) {
                case 1:
                    // View Load Assignments
                    List<Subject> assignedSubjects = loadRepo.getSubjectsByFacultyId(loggedInFaculty.getFacultyId());
                    facultyLoadView.showFacultyLoads(loggedInFaculty, assignedSubjects);
                    break;
                case 2:
                    // Update availabiity
                    boolean isAvailable = dashboardView.promptAvailabilityUpdate();
                    facultyRepo.updateAvailability(loggedInFaculty.getFacultyId(), isAvailable);
                    System.out.println("Availability status updated successfully.");
                    break;
                case 0:
                    // Logout
                    loggedInFaculty = null;
                    running = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
