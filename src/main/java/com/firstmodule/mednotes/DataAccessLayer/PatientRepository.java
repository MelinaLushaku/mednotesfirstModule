package com.firstmodule.mednotes.DataAccessLayer;

import com.firstmodule.mednotes.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("select a from Patient a where a.email=?1 and a.password=?2")
    Patient findPatientByUsernameAndPassword(String username, String pass);
}
