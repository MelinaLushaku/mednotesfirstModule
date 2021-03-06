package com.firstmodule.mednotes.DataAccessLayer;

import com.firstmodule.mednotes.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query("select a from Doctor a where a.email=?1 and a.password=?2")
    Optional<Doctor> findDoctorByUsernameAndPassword(String username, String pass);

    @Query("select a from Doctor a where a.email=?1")
    List <Doctor> findDoctorByEmail(String username);


    @Query("select d from Doctor d where d.personalNumber=?1 ")
    List<Doctor> findDByPersonalNumber(int nrPersonal);

    @Query("select d from Doctor d where d.department=?1 ")
    List<Doctor> findDByDepartment(int department);

    @Query("select a from  Doctor a where a.doctorName=?1")
    List<Doctor>searchDocByName(String name);



}
