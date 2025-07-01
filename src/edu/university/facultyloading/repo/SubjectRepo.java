package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Subject;
import java.util.List;

public interface SubjectRepo {
    void create(Subject subject);

    Subject getById(int subjectId);

    List<Subject> getAll();

    void update(Subject subject);

    void archive(int subjectId);

    void restore(int subjectId);

    void delete(int subjectId);
}
