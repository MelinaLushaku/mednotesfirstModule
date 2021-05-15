package com.firstmodule.mednotes.DataAccessLayer;

import com.firstmodule.mednotes.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("select a from Admin a where a.email=?1 and a.password=?2")
    Admin findAdminByUsernameAndPassword(String username, String pass);
    @Query("select a from Admin a where a.adminName=?1")
    Admin findAdminByName(String name);
}
