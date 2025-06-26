package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Admin;
import java.util.List;

public interface AdminRepo {

    public Admin fetchAdmin(int adminId);

    public Admin fetchAdmin(String username, String password);

    public List<Admin> fetchAdmins();

    public boolean createAdmin(String username, String password, String firstName, String lastName);

    public boolean updateUserProfile(int id, String username, String password, String firstName, String lastName);

    public boolean archiveAdmin(int id);

    public boolean restoreAdmin(int id);

    public boolean deleteAdmin(int id, int adminId);
}
