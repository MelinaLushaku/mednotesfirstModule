package com.firstmodule.mednotes.DataAccessLayer;

import com.firstmodule.mednotes.Model.Department;
import com.firstmodule.mednotes.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department , Integer> {
    @Query("select a from Department a where a.depName=?1")
    List<Department> findDepartmentByName(String name);

}
