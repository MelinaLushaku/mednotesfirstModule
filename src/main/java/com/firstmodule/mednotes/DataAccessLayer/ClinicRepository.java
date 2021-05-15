package com.firstmodule.mednotes.DataAccessLayer;

import com.firstmodule.mednotes.Model.Clinic;
import com.firstmodule.mednotes.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
    @Query("select a from Clinic a where a.name=?1")
    Clinic getClinicByName(String name);

}
