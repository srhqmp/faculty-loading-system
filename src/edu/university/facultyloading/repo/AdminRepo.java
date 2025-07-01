package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Admin;
import java.util.List;

public interface AdminRepo {

    Admin create(Admin admin);

    Admin authenticate(String username, String password);

    Admin getById(int adminId);

    List<Admin> getAll();

    void update(Admin admin);

    void archive(int adminId);

    void restore(int adminId);

    void delete(int adminId);

}
