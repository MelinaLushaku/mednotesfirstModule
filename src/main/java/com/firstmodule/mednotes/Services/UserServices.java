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
    @Autowired
    private AdvertisementRepository advertisementRepository;



    public Optional <Admin> loginA(String username , String pass){
        Optional <Admin> a = this.adminRepository.findAdminByUsernameAndPassword(username, pass);
        if (a == null){
            return null;
        }
        return a;
    }

    public Optional<Doctor> loginD(String username , String pass){
        Optional<Doctor> d = this.doctorRepository.findDoctorByUsernameAndPassword(username, pass);
        if (d == null){
            return null;
        }
        return d;
    }

    public Optional<Patient> loginP(String username , String pass){
        Optional<Patient> p = this.patientRepository.findPatientByUsernameAndPassword(username, pass);
        if (p == null){
            return null;
        }
        return p;
    }

    @Override
    public void registerD(Doctor d) {
        this.doctorRepository.save(d);
    }
    @Override
    public void registerP(Patient p){
        this.patientRepository.save(p);
    }
    @Override
    public Optional<Department> finDepById(int id){
        Optional<Department> dep = this.departmentRepository.findById(id);
        return dep;
    }
    @Override
    public Optional<Clinic> finClinById(int id){
        Optional<Clinic> cl = this.clinicRepository.findById(id);
        return cl;
    }
    @Override
    public List<Doctor> findDByPN(int nrPersonal){
        List <Doctor> lista =this.doctorRepository.findDByPersonalNumber(nrPersonal);
        return lista;
    }
    @Override
    public List<Patient> findPByPN(int nrPersonal){
        List <Patient> lista =this.patientRepository.findPByPersonalNumber(nrPersonal);
        return lista;
    }
    @Override
    public List<Patient> findPatientByEmail(String email){
        List <Patient> lista =this.patientRepository.findPatientByEmail(email);
        return lista;
    }
    @Override
    public List<Doctor> findDoctorByEmail(String email){
        List <Doctor> lista =this.doctorRepository.findDoctorByEmail(email);
        return lista;
    }
    @Override
    public void deleteDoctor(Doctor d){
        this.doctorRepository.delete(d);

    }
    @Override
    public void deletePatient(Patient p){
        this.patientRepository.delete(p);
    }
    @Override
    public List<Doctor> findAllD(){
        return this.doctorRepository.findAll();
    }
    @Override
    public List<Patient> findAllP(){
        return this.patientRepository.findAll();
    }

    @Override
    public int getTotalDoctor(){
        List <Doctor> doc = this.doctorRepository.findAll();
        return  doc.size();

    }
    @Override
    public int getTotalPatient(){
        List <Patient> pat = this.patientRepository.findAll();
        return  pat.size();
    }

    @Override
    public int getTotalDep(){
        List <Department> dep = this.departmentRepository.findAll();
        return  dep.size();
    }
    @Override
    public List <Department>findAllDep(){
        return this.departmentRepository.findAll();
    }
    @Override
    public void addDepartment(Department d){
        this.departmentRepository.save(d);
    }
    @Override
    public List<Department> findByName(String name){
        return  this.departmentRepository.findDepartmentByName(name);
    }
    @Override
    public void deleteDep(Department d){
        this.departmentRepository.delete(d);
    }

    @Override
    public void addAdvert(Advertisement a){
        this.advertisementRepository.save(a);
    }
    @Override
    public void deleteAdvert(Advertisement a){
        this.advertisementRepository.delete(a);
    }
    @Override
    public List<Advertisement> getByTitle(String title){
        return this.advertisementRepository.findByTitle(title);
    }
    public Admin getByName(String name){
        return this.adminRepository.findAdminByName(name);
    }
    @Override
    public void updateClinicsInfos(String address , String phone , String email , int nrPartners){
        Optional<Clinic> c = this.clinicRepository.getClinicByName("MedNotes");
        Clinic ca = c.get();
        ca.setAdrres(address);
        ca.setEmail(email);
        ca.setPartners(nrPartners);
        ca.setPhone(phone);
        this.clinicRepository.save(ca);

    }
    @Override
    public List<Clinic> getAllC(){
        return this.clinicRepository.findAll();
    }
    @Override
    public void editDep(String name, int nrDhomav){
        List<Department> dep = this.departmentRepository.findDepartmentByName(name);
        Department d = dep.get(0);
        d.setNumOfRooms(nrDhomav);

    }
    @Override
    public Optional <Clinic> getClinicByName(String name){
        return  this.clinicRepository.getClinicByName(name);
    }
}

