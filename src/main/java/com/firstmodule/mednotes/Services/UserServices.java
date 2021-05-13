package com.firstmodule.mednotes.Services;

import com.firstmodule.mednotes.DataAccessLayer.*;
import com.firstmodule.mednotes.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices implements UserServiceInterface {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ClinicRepository clinicRepository;



    public Admin loginA(String username , String pass){
        Admin a = this.adminRepository.findAdminByUsernameAndPassword(username, pass);
        if (a == null){
            return null;
        }
        return a;
    }

    public Doctor loginD(String username , String pass){
        Doctor d = this.doctorRepository.findDoctorByUsernameAndPassword(username, pass);
        if (d == null){
            return null;
        }
        return d;
    }

    public Patient loginP(String username , String pass){
        Patient p = this.patientRepository.findPatientByUsernameAndPassword(username, pass);
        if (p == null){
            return null;
        }
        return p;
    }

    @Override
    public void registerD(Doctor d) {
        doctorRepository.save(d);
    }
    @Override
    public void registerP(Patient p){
        patientRepository.save(p);
    }

    public Optional<Department> finDepById(int id){
        Optional<Department> dep = this.departmentRepository.findById(id);
        return dep;
    }

    public Optional<Clinic> finClinById(int id){
        Optional<Clinic> cl = this.clinicRepository.findById(id);
        return cl;
    }

    public List<Doctor> findDByPN(int nrPersonal){
        List <Doctor> lista =this.doctorRepository.findDByPersonalNumber(nrPersonal);
        return lista;
    }
    public List<Patient> findPByPN(int nrPersonal){
        List <Patient> lista =this.patientRepository.findPByPersonalNumber(nrPersonal);
        return lista;
    }
    public List<Patient> findPatientByEmail(String email){
        List <Patient> lista =this.patientRepository.findPatientByEmail(email);
        return lista;
    }
    public List<Doctor> findDoctorByEmail(String email){
        List <Doctor> lista =this.doctorRepository.findDoctorByEmail(email);
        return lista;
    }

}
