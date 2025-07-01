package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Faculty;
import java.util.List;

public interface FacultyRepo {
    void create(Faculty faculty);

    Faculty authenticate(String username, String password);

    Faculty getById(int facultyId);

    List<Faculty> getAll();

    void update(Faculty faculty);

    void archive(int facultyId);

    void restore(int facultyId);

    void delete(int facultyId);
}
