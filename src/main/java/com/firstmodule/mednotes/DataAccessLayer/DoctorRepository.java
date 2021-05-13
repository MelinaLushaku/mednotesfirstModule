package com.firstmodule.mednotes.DataAccessLayer;

import com.firstmodule.mednotes.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query("select a from Doctor a where a.email=?1 and a.password=?2")
    Doctor findDoctorByUsernameAndPassword(String username, String pass);

    @Query("select a from Doctor a where a.email=?1")
    List <Doctor> findDoctorByEmail(String username);


    @Query("select d from Doctor d where d.personalNumber=?1 ")
    List<Doctor> findDByPersonalNumber(int nrPersonal);
}
