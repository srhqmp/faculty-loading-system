package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Subject;
import java.util.List;

public interface SubjectRepo {

    public Subject fetchSubject(int id);

    public List<Subject> fetchSubjects();

    public boolean createSubject(String name, String description);

    public boolean updateSubject(int id, String name, String description);

    public boolean archiveSubject(int id);

    public boolean restoreSubject(int id);

    public boolean deleteSubject(int id);
}
