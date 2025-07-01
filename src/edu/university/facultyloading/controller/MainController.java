package edu.university.facultyloading.controller;

import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.AdminRepo;
import edu.university.facultyloading.repo.FacultyRepo;
import edu.university.facultyloading.repo.LoadRepo;
import edu.university.facultyloading.repo.SubjectRepo;
import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.view.AssignSubjectView;
import edu.university.facultyloading.view.DashboardView;
import edu.university.facultyloading.view.FacultyListView;
import edu.university.facultyloading.view.FacultyLoadView;
import edu.university.facultyloading.view.LoginView;
import edu.university.facultyloading.view.RegisterView;
import edu.university.facultyloading.view.SubjectListView;
import edu.university.facultyloading.view.SubjectManagementView;

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
            String[] header = {
                    "╔════════════════════════════════════════════════════════════════════╗",
                    "║                            WELCOME TO                              ║",
                    "║                                                                    ║",
                    "║    ███████╗ █████╗  ██████╗██╗   ██╗██╗     ████████╗██╗   ██╗     ║",
                    "║    ██╔════╝██╔══██╗██╔════╝██║   ██║██║     ╚══██╔══╝╚██╗ ██╔╝     ║",
                    "║    █████╗  ███████║██║     ██║   ██║██║        ██║    ╚████╔╝      ║",
                    "║    ██╔══╝  ██╔══██║██║     ██║   ██║██║        ██║     ╚██╔╝       ║",
                    "║    ██║     ██║  ██║╚██████╗╚██████╔╝███████╗   ██║      ██║        ║",
                    "║    ╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚═════╝ ╚══════╝   ╚═╝      ╚═╝        ║",
                    "║                                                                    ║",
                    "║                           LOADING SYSTEM                           ║",
                    "╚════════════════════════════════════════════════════════════════════╝"
            };
            System.out.println();
            for (String line : header) {
                System.out.println(OutputFormatter.centerString(line));
            }
            System.out.println();

            System.out.println(OutputFormatter.centerString("╔══════════════════════════════╗"));
            System.out.println(OutputFormatter.centerString("║           MAIN MENU          ║"));
            System.out.println(OutputFormatter.centerString("╠══════════════════════════════╣"));
            System.out.println(OutputFormatter.centerString("║ 1. Login                     ║"));
            System.out.println(OutputFormatter.centerString("║ 2. Register                  ║"));
            System.out.println(OutputFormatter.centerString("╚══════════════════════════════╝"));
            System.out.print(OutputFormatter.centerString("Choose an option: "));

            int choice = -1;

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(OutputFormatter.centerString("Invalid input. Please try again."));
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
                    System.out.println(OutputFormatter.centerString("Invalid choice. Please try again."));
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
            System.out.println(OutputFormatter.centerString("Welcome " + loggedInAdmin.getFullname() + "!"));
            displayAdminDashboard();
            return;
        }

        loggedInFaculty = facultyRepo.authenticate(username, password);
        if (loggedInFaculty != null) {
            System.out.println(OutputFormatter.centerString("Welcome " + loggedInFaculty.getFullname() + "!"));
            displayFacultyDashboard();
            return;
        }

        loginView.showLoginFailed();
    }

    private void handleRegistration() {
        String[] data = registerView.showRegisterPrompt();

        int userType;
        try {
            userType = Integer.parseInt(data[0]);
            if (userType != 1 && userType != 2) {
                System.out.println(OutputFormatter.centerString("Invalid user type."));
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(OutputFormatter.centerString("User type must be a number."));
            return;
        }

        String username = data[1].trim();
        String password = data[2].trim();
        String firstName = data[3].trim();
        String lastName = data[4].trim();

        // Basic validation
        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            System.out.println(OutputFormatter.centerString("All fields must be filled."));
            return;
        }

        if (userType == 1) {
            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(password);
            admin.setFirstName(firstName);
            admin.setLastName(lastName);
            adminRepo.create(admin);

            System.out.println(OutputFormatter.centerString("Admin registered successfully."));
            System.out.println();
            System.out.println();

            loggedInAdmin = adminRepo.authenticate(username, password);
            if (loggedInAdmin != null) {
                System.out.println(OutputFormatter.centerString("Welcome " + loggedInAdmin.getFullname() + "!"));
                displayAdminDashboard();
            }
        } else {
            String major = data[5].trim();
            String yearsInput = data[6].trim();

            if (major.isEmpty() || yearsInput.isEmpty()) {
                System.out.println(OutputFormatter.centerString("Major and experience must be filled."));
                return;
            }

            int yearsOfExperience;
            try {
                yearsOfExperience = Integer.parseInt(yearsInput);
                if (yearsOfExperience < 0) {
                    System.out.println(OutputFormatter.centerString("Years of experience must be non-negative."));
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println(OutputFormatter.centerString("Years of experience must be a number."));
                return;
            }

            Faculty faculty = new Faculty();
            faculty.setUsername(username);
            faculty.setPassword(password);
            faculty.setFirstName(firstName);
            faculty.setLastName(lastName);
            faculty.setMajor(major);
            faculty.setYearsOfExperience(yearsOfExperience);
            facultyRepo.create(faculty);

            System.out.println(OutputFormatter.centerString("Faculty registered successfully."));
            System.out.println();
            System.out.println();

            loggedInFaculty = facultyRepo.authenticate(username, password);
            if (loggedInFaculty != null) {
                System.out.println(OutputFormatter.centerString("Welcome " + loggedInFaculty.getFullname() + "!"));
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
                    int subjectId = assignSubjectView.promptSubjectIdToAssign();
                    boolean success = loadRepo.assignSubjectToFaculty(facultyId, subjectId);
                    if (success)
                        assignSubjectView.showAssignmentSuccess();
                    else
                        assignSubjectView.showAssignmentFailed();
                    break;
                case 4:
                    // Remove Assigned Subject from Faculty
                    facultyListView.showFaculties(faculties);
                    int facultyIdToRemove = assignSubjectView.promptFacultyId();

                    // Get assigned subjects for the faculty
                    List<Subject> assignedSubjectsToRemove = loadRepo.getSubjectsByFacultyId(facultyIdToRemove);

                    if (assignedSubjectsToRemove.isEmpty()) {
                        System.out.println("No subjects assigned to this faculty.");
                        break;
                    }

                    subjectListView.showSubjects(assignedSubjectsToRemove);
                    int subjectIdToRemove = assignSubjectView.promptSubjectIdToRemove();

                    boolean removeSuccess = loadRepo.removeSubjectFromFaculty(facultyIdToRemove, subjectIdToRemove);
                    if (removeSuccess)
                        assignSubjectView.showRemoveAssignmentSuccess();
                    else
                        assignSubjectView.showRemoveAssignmentFailed();
                    break;
                case 5:
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
                    System.out.println(OutputFormatter.centerString("Logged out."));
                    break;
                default:
                    System.out.println(OutputFormatter.centerString("Invalid choice. Please try again."));
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
                    System.out.println(OutputFormatter.centerString("Logged out."));
                    break;
                default:
                    System.out.println(OutputFormatter.centerString("Invalid choice. Please try again."));
            }
        }
    }
}
