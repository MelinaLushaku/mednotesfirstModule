package com.firstmodule.mednotes.DataAccessLayer;

import com.firstmodule.mednotes.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department , Integer> {

}
