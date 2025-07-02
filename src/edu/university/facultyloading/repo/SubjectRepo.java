package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Subject;
import java.util.List;

public interface SubjectRepo {
    boolean create(Subject subject);

    Subject getById(int subjectId);

    List<Subject> getAll();

    boolean update(Subject subject);

    boolean archive(int subjectId);

    boolean restore(int subjectId);

    boolean delete(int subjectId);
}
