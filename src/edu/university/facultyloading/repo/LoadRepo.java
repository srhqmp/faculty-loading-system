package edu.university.facultyloading.repo;

import edu.university.facultyloading.model.Load;
import java.util.List;

public interface LoadRepo {

    public Load fetchLoad(int id);

    public List<Load> fetchLoads();

    public boolean createLoad(int facultyId);

    public boolean approveLoad(int id, int registrarId);

    public boolean archiveLoad(int id);

    public boolean restoreLoad(int id);

    public boolean deleteLoad(int id);
}
