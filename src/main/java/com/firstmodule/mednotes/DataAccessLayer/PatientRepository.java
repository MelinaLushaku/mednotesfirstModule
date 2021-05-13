package com.firstmodule.mednotes.DataAccessLayer;

import com.firstmodule.mednotes.Model.Doctor;
import com.firstmodule.mednotes.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("select a from Patient a where a.email=?1 and a.password=?2")
    Patient findPatientByUsernameAndPassword(String username, String pass);

    @Query("select p from Patient p where p.personalNumber=?1 ")
    List<Patient> findPByPersonalNumber(int nrPersonal);

    @Query("select a from Patient a where a.email=?1")
    List <Patient> findPatientByEmail(String username);
}
