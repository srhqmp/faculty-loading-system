/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Admin;
import java.util.List;

/**
 *
 * @author user
 */
public interface AdminRepo {

    public Admin fetchAdmin(int adminId);

    public List<Admin> fetchAdmins();

    public boolean createAdmin(int adminId, String username, String password, String firstName, String lastName);

    public boolean updateAdminProfile(int adminId, String firstName, String lastName);

    public boolean archiveAdmin(int adminId);

    public boolean restoreAdmin(int adminId);

    public boolean deleteAdmin(int adminId);
}
