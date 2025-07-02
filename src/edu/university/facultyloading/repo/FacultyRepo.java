package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Faculty;
import java.util.List;

public interface FacultyRepo {
    Faculty create(Faculty faculty);

    Faculty authenticate(String username, String password);

    Faculty getById(int facultyId);

    List<Faculty> getAll();

    boolean update(Faculty faculty);

    boolean updateAvailability(int facultyId, boolean isAvailable);

    boolean archive(int facultyId);

    boolean restore(int facultyId);

    boolean delete(int facultyId);
}
