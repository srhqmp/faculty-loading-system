/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Registrar;

/**
 *
 * @author user
 */
public interface RegistrarRepo {

    public Registrar fetchRegistrar(int registrarId);

    public boolean createRegistrar(int registrarId, String username, String password, String firstName, String lastName);

    public boolean updateRegistrarProfile(int registrarId, String firstName, String lastName);

    public boolean archiveRegistrar(int registrarId);

    public boolean restoreRegistrar(int registrarId);

    public boolean deleteRegistrar(int registrarId);
}
