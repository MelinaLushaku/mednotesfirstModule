package com.firstmodule.mednotes.Services;

import com.firstmodule.mednotes.DataAccessLayer.AdminRepository;
import com.firstmodule.mednotes.DataAccessLayer.DoctorRepository;
import com.firstmodule.mednotes.DataAccessLayer.PatientRepository;
import com.firstmodule.mednotes.Model.Admin;
import com.firstmodule.mednotes.Model.Doctor;
import com.firstmodule.mednotes.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
@Service
public class UserServices {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;



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
}
