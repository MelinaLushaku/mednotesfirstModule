package com.firstmodule.mednotes.Services;

import com.firstmodule.mednotes.Model.*;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    Admin loginA(String username , String pass);
    Doctor loginD(String username , String pass);
    Patient loginP(String username , String pass);
    void registerD(Doctor d);
    void registerP(Patient p);
    Optional<Department> finDepById(int id);
    Optional<Clinic> finClinById(int id);
    List<Doctor> findDByPN(int nrPersonal);
    List<Patient> findPByPN(int nrPersonal);
    List<Patient> findPatientByEmail(String email);
    List<Doctor> findDoctorByEmail(String email);
    void deleteDoctor(Doctor d);
    void deletePatient(Patient p);
    int getTotalPatient();
    int getTotalDoctor();
    int getTotalDep();
    List<Doctor> findAllD();
    List<Patient> findAllP();
    List<Department> findAllDep();
    void addDepartment(Department d);
    List<Department> findByName(String name);
    void deleteDep(Department d);
    void addAdvert(Advertisement a);
    void deleteAdvert(Advertisement a);
    List<Advertisement> getByTitle(String title);
    Admin getByName(String name);


}
