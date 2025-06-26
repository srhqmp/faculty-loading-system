package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Registrar;
import java.util.List;

public interface RegistrarRepo {

    public Registrar fetchRegistrar(int registrarId);

    public List<Registrar> fetchRegistrars();

    public boolean createRegistrar(String username, String password, String firstName, String lastName);

    public boolean updateUserProfile(int id, String username, String password, String firstName, String lastName);

    public boolean archiveRegistrar(int id);

    public boolean restoreRegistrar(int id);

    public boolean deleteRegistrar(int id, int registrarId);
}
