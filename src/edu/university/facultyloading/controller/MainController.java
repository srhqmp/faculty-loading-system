package edu.university.facultyloading.controller;

import edu.university.facultyloading.model.Admin;
import edu.university.facultyloading.model.Faculty;
import edu.university.facultyloading.model.Subject;
import edu.university.facultyloading.repo.AdminRepo;
import edu.university.facultyloading.repo.FacultyRepo;
import edu.university.facultyloading.repo.LoadRepo;
import edu.university.facultyloading.repo.SubjectRepo;
import edu.university.facultyloading.util.HelperMethods;
import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.util.PromptMessage;
import edu.university.facultyloading.view.AssignSubjectView;
import edu.university.facultyloading.view.DashboardView;
import edu.university.facultyloading.view.FacultyListView;
import edu.university.facultyloading.view.FacultyLoadView;
import edu.university.facultyloading.view.LoginView;
import edu.university.facultyloading.view.MainMenuView;
import edu.university.facultyloading.view.RegisterView;
import edu.university.facultyloading.view.SubjectManagementView;

import java.util.List;
import java.util.Scanner;

public class MainController {
    private final Scanner scanner = new Scanner(System.in);

    private final SubjectController subjectController;
    private final AdminController adminController;
    private final FacultyController facultyController;
    private final RegistrationController registrationController;
    private final LoadController loadController;

    private final LoginView loginView = new LoginView(scanner);
    private final DashboardView dashboardView = new DashboardView(scanner);
    private final FacultyListView facultyListView = new FacultyListView();
    private final AssignSubjectView assignSubjectView = new AssignSubjectView(scanner);
    private final FacultyLoadView facultyLoadView = new FacultyLoadView();
    private final RegisterView registerView = new RegisterView(scanner);
    private final MainMenuView mainMenuView = new MainMenuView();

    private Admin loggedInAdmin = null;
    private Faculty loggedInFaculty = null;

    public MainController(AdminRepo adminRepo, FacultyRepo facultyRepo, SubjectRepo subjectRepo,
            LoadRepo loadRepo) {
        this.subjectController = new SubjectController(subjectRepo, loadRepo);
        this.adminController = new AdminController(adminRepo);
        this.facultyController = new FacultyController(facultyRepo);
        this.registrationController = new RegistrationController(adminController, facultyController);
        this.loadController = new LoadController(loadRepo);
    }

    public void start() {
        boolean running = true;
        while (running) {
            mainMenuView.show();

            int choice = -1;

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                PromptMessage.errorMessage("Invalid input. Please try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    handleRegistration();
                    break;
                case 0:
                    running = false;
                    PromptMessage.successMessage("Exiting the program. Goodbye!");
                    break;
                default:
                    PromptMessage.errorMessage("Invalid choice. Please try again.");
            }
        }
    }

    private void handleLogin() {
        String[] credentials = loginView.showLoginPrompt();
        String username = credentials[0];
        String password = credentials[1];

        handleLogin(username, password);
    }

    private void handleLogin(String username, String password) {
        loggedInAdmin = adminController.login(username, password);

        if (loggedInAdmin != null) {
            PromptMessage.successMessage("Welcome " +
                    loggedInAdmin.getFullname() + "!");
            displayAdminDashboard();
            return;
        }

        loggedInFaculty = facultyController.authenticate(username, password);
        if (loggedInFaculty != null) {
            PromptMessage.successMessage("Welcome " +
                    loggedInFaculty.getFullname() + "!");
            displayFacultyDashboard();
            return;
        }

        loginView.showLoginFailed();
    }

    private void handleRegistration() {
        String[] data = registerView.showRegisterPrompt();
        boolean success = registrationController.registerUser(data);

        if (!success)
            return;

        String username = data[1].trim();
        String password = data[2].trim();

        handleLogin(username, password);
    }

    private void displayAdminDashboard() {
        boolean running = true;
        while (running) {
            int choice = dashboardView.showAdminDashboard();

            // Provide faculties and subjects
            List<Faculty> faculties = facultyController.getAllFaculties();
            for (Faculty faculty : faculties) {
                // get faculty load
                List<Subject> assignedSubjects = loadController.getSubjectsByFacultyId(faculty.getFacultyId());
                faculty.setAssignedSubjects(assignedSubjects);
            }
            List<Subject> subjects = subjectController.getAllSubjects();
            SubjectManagementView subjectManagementView = new SubjectManagementView(scanner, subjectController);

            switch (choice) {
                case 1: // View all Faculties
                    facultyListView.showFaculties(faculties);
                    break;
                case 2: // Manage Subjects
                    subjectManagementView.showSubjectManagementMenu();
                    break;
                case 3: // Assign Subjects to Faculty
                    facultyListView.showFaculties(faculties);
                    int facultyId = assignSubjectView.promptFacultyId();

                    if (!HelperMethods.facultyInTheList(facultyId, faculties)) {
                        PromptMessage.errorMessage("Faculty with ID " + facultyId + " does not exist.");
                        break;
                    }

                    subjectManagementView.viewAllSubjects(subjects);
                    int subjectId = assignSubjectView.promptSubjectIdToAssign();

                    if (!HelperMethods.subjectInTheList(subjectId, subjects)) {
                        PromptMessage.errorMessage("Subject with ID " + subjectId + " does not exist.");
                        break;
                    }

                    boolean success = loadController.assignSubjectToFaculty(facultyId, subjectId);

                    if (success)
                        assignSubjectView.showAssignmentSuccess();
                    else
                        assignSubjectView.showAssignmentFailed();
                    break;
                case 4: // Assign the best faculty to a subject
                    subjectManagementView.viewAllSubjects(subjects);
                    int subjectIdToAssign = assignSubjectView.promptSubjectIdToAssign();

                    if (!HelperMethods.subjectInTheList(subjectIdToAssign, subjects)) {
                        PromptMessage.errorMessage("Subject with ID " + subjectIdToAssign + " does not exist.");
                        break;
                    }

                    for (Subject subject : subjects) {
                        if (subject.getSubjectId() == subjectIdToAssign) {
                            loadController.assignFaculty(faculties, subject);
                        }
                    }
                    break;
                case 5: // Remove Assigned Subject from Faculty
                    facultyListView.showFaculties(faculties);
                    int facultyIdToRemove = assignSubjectView.promptFacultyId();

                    if (!HelperMethods.facultyInTheList(facultyIdToRemove, faculties)) {
                        PromptMessage.errorMessage("Faculty with ID " + facultyIdToRemove + " does not exist.");
                        break;
                    }

                    // Get assigned subjects for the faculty
                    List<Subject> assignedSubjectsToRemove = loadController.getSubjectsByFacultyId(facultyIdToRemove);

                    if (assignedSubjectsToRemove.isEmpty()) {
                        PromptMessage.errorMessage("No subjects assigned to this faculty.");
                        break;
                    }

                    subjectManagementView.viewAllSubjects(assignedSubjectsToRemove);
                    int subjectIdToRemove = assignSubjectView.promptSubjectIdToRemove();

                    if (!HelperMethods.subjectInTheList(subjectIdToRemove, assignedSubjectsToRemove)) {
                        PromptMessage.errorMessage("Subject with ID " + subjectIdToRemove
                                + " does not exist in the subject load.");
                        break;
                    }

                    boolean removeSuccess = loadController.removeSubjectFromFaculty(facultyIdToRemove,
                            subjectIdToRemove);
                    if (removeSuccess)
                        assignSubjectView.showRemoveAssignmentSuccess();
                    else
                        assignSubjectView.showRemoveAssignmentFailed();
                    break;
                case 6: // View Faculty Loads
                    facultyListView.showFaculties(faculties);

                    int viewFacultyId = assignSubjectView.promptFacultyId();
                    Faculty faculty = facultyController.getFaculty(viewFacultyId);

                    if (!HelperMethods.facultyInTheList(viewFacultyId, faculties)) {
                        PromptMessage.errorMessage("Faculty with ID " + viewFacultyId + " does not exist.");
                        break;
                    }

                    if (faculty != null) {
                        List<Subject> assignedSubjects = loadController.getSubjectsByFacultyId(viewFacultyId);
                        facultyLoadView.showFacultyLoads(faculty, assignedSubjects);
                    } else {
                        PromptMessage.errorMessage("Faculty not found.");
                    }
                    break;
                case 0: // Logout
                    loggedInAdmin = null;
                    running = false;
                    PromptMessage.successMessage(OutputFormatter.centerString("Logged out."));
                    break;
                default:
                    PromptMessage.errorMessage(OutputFormatter.centerString("Invalid choice. Please try again."));
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
                    List<Subject> assignedSubjects = loadController
                            .getSubjectsByFacultyId(loggedInFaculty.getFacultyId());
                    facultyLoadView.showFacultyLoads(loggedInFaculty, assignedSubjects);
                    break;
                case 2:
                    // Update availabiity
                    boolean isAvailable = dashboardView.promptAvailabilityUpdate();
                    facultyController.updateAvailability(loggedInFaculty.getFacultyId(), isAvailable);
                    PromptMessage.successMessage("Availability status updated successfully.");
                    break;
                case 0:
                    // Logout
                    loggedInFaculty = null;
                    running = false;
                    PromptMessage.successMessage(OutputFormatter.centerString("Logged out."));
                    break;
                default:
                    PromptMessage.errorMessage(OutputFormatter.centerString("Invalid choice. Please try again."));
            }
        }
    }
}
