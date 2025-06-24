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

    public int fetchID();

    public List<Admin> fetchAdmins();
    
    public Admin fetchAdmin();
}
