package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Admin;
import java.util.List;

public interface AdminRepo {

    Admin create(Admin admin);

    Admin authenticate(String username, String password);

    Admin getById(int adminId);

    List<Admin> getAll();

    boolean update(Admin admin);

    boolean archive(int adminId);

    boolean restore(int adminId);

    boolean delete(int adminId);

}
