package com.firstmodule.mednotes.Controller;

import com.firstmodule.mednotes.Helper.DepartmentHelper;
import com.firstmodule.mednotes.Helper.RegisterHelper;
import com.firstmodule.mednotes.Model.*;
import com.firstmodule.mednotes.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/systemManagement")
public class SystemManagementModule {
    @Autowired
    private UserServices us;

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity loginUser(@PathVariable String email, @PathVariable String password) {
        Admin a = us.loginA(email, password);
        Doctor d = us.loginD(email, password);
        Patient p = us.loginP(email, password);
        if (a != null) {
            return ResponseEntity.ok(a);
        } else if (d != null) {
            return ResponseEntity.ok(d);
        } else if (p != null) {
            return ResponseEntity.ok(p);
        }
        return ResponseEntity.ok("This user doesnt exists!");

    }

    @PostMapping("registerUser")
    public ResponseEntity registerU(@RequestBody RegisterHelper rh) {

        if (!rh.getName().isEmpty() && !rh.getSurname().isEmpty() && !rh.getEmail().isEmpty() && !rh.getPassword().isEmpty() && rh.getPersonalNumber() != 0) {
            if (rh.getRole() == 1 && !rh.getSpecializationD().isEmpty() && !rh.getDepartmentD().isEmpty()) {
                Optional<Department> dep = this.us.finDepById(rh.getDep_id());
                Optional<Clinic> c = this.us.finClinById(3);
                if (dep.isPresent()) {
                    List<Doctor> listt = this.us.findDByPN(rh.getPersonalNumber());
                    List<Patient> lista = this.us.findPByPN(rh.getPersonalNumber());
                    if (listt.size() == 0 && lista.size() == 0) {
                        List<Doctor> lista2 = this.us.findDoctorByEmail(rh.getEmail());
                        List<Patient> lista3 = this.us.findPatientByEmail(rh.getEmail());
                        if (lista2.size() == 0 && lista3.size() == 0) {
                            Doctor d = new Doctor(rh.getName(), rh.getSurname(), rh.getSpecializationD(), rh.getDepartmentD(), rh.getEmail(), rh.getPassword(), dep.get(), c.get(), rh.getPersonalNumber(), rh.getRole());
                            this.us.registerD(d);
                            return ResponseEntity.ok("Welcome to MedNotes");
                        }
                        return ResponseEntity.ok("This email is being used by someone else");
                    } else {
                        return ResponseEntity.ok("This personalNumber belongs to someone else");
                    }
                } else {
                    return ResponseEntity.ok("This department doesn't exists");
                }
            } else if (rh.getRole() == 2) {
                Optional<Clinic> c = this.us.finClinById(3);
                List<Patient> lista = this.us.findPByPN(rh.getPersonalNumber());
                List<Doctor> listt = this.us.findDByPN(rh.getPersonalNumber());
                if (lista.size() == 0 && listt.size() == 0) {
                    List<Patient> lista2 = this.us.findPatientByEmail(rh.getEmail());
                    List<Doctor> lista3 = this.us.findDoctorByEmail(rh.getEmail());
                    if (lista2.size() == 0 && lista3.size() == 0) {
                        Patient p = new Patient(rh.getName(), rh.getSurname(), rh.getEmail(), rh.getPersonalNumber(), rh.getPassword(), rh.getRole(), c.get());

                        this.us.registerP(p);
                        return ResponseEntity.ok("Welcome to MedNotes");
                    }
                    return ResponseEntity.ok("This email is being used by someone else");
                } else {
                    return ResponseEntity.ok("This personalNumber belongs to someone else");
                }
            }
        }
        return ResponseEntity.ok("Fill all the fields");

    }

    @PostMapping("/admin/deleteUser/{personalNumber}")
    public ResponseEntity deleteUser(@PathVariable int personalNumber) {
        List<Doctor> doc = this.us.findDByPN(personalNumber);
        List<Patient> pat = this.us.findPByPN(personalNumber);
        if (doc.size() == 0 && pat.size() == 0) {
            return ResponseEntity.ok("There is no user with this personal number!");
        } else if (doc.size() != 0) {
            Doctor d = doc.get(0);
            this.us.deleteDoctor(d);
            return ResponseEntity.ok("Doctor with personalNumber:" + personalNumber + "is deleted");
        } else {
            Patient p = pat.get(0);
            this.us.deletePatient(p);
            return ResponseEntity.ok("Patient with personalNumber:" + personalNumber + "is deleted");
        }

    }

    @GetMapping("/admin/totalNumberOfDoc")
    public ResponseEntity totalDoc() {
        int totalD = this.us.getTotalDoctor();
        return ResponseEntity.ok(totalD);
    }

    @GetMapping("/admin/totalNumberOfPat")
    public ResponseEntity totalPat() {
        int totalP = this.us.getTotalPatient();
        return ResponseEntity.ok(totalP);
    }

    @GetMapping("/admin/totalNumberOfUser")
    public ResponseEntity totalUsers() {
        int totalD = this.us.getTotalDoctor();
        int totalP = this.us.getTotalPatient();
        int totalU = totalD + totalP;
        return ResponseEntity.ok(totalU);
    }

    @GetMapping("/admin/totalNumberOfDep")
    public ResponseEntity totalDep() {
        int totalDep = this.us.getTotalDep();
        return ResponseEntity.ok(totalDep);
    }
    //error per pk edhe fk
    @GetMapping("/admin/ListOfDep")
    public ResponseEntity listOfDep() {
        List<Department> list = this.us.findAllDep();
        if (list.size() == 0) {
            return ResponseEntity.ok("There is no department");
        } else {
            return ResponseEntity.ok(list);
        }
    }
    @PostMapping("/admin/addDep")
    public ResponseEntity addDepartment(@RequestBody DepartmentHelper departmentHelper){
        Optional<Clinic> cc = this.us.finClinById(3);
        List<Department> lista =this.us.findByName(departmentHelper.getDepName());
        if(lista.size() == 0) {
            Department d = new Department(departmentHelper.getDepName(), departmentHelper.getNumberOfRooms(), cc.get());
            this.us.addDepartment(d);
            return ResponseEntity.ok("Department added successfully");
        }
        return ResponseEntity.ok("This Department Exists");
    }
    //error per pk edhe fk
    @PostMapping ("/admin/deleteDep/{depName}")
    public ResponseEntity deleteDepartment(@PathVariable String depName ){
        List<Department> dep = this.us.findByName(depName);
        if(dep.size() != 0){
            this.us.deleteDep(dep.get(0));
            return ResponseEntity.ok("Department deleted successfully!");
        }
        return ResponseEntity.ok("This department doesn't exists!");
        }
    }

