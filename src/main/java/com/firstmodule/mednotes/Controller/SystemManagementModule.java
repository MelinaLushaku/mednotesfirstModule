package com.firstmodule.mednotes.Controller;

import com.firstmodule.mednotes.Helper.AdminResponse;
import com.firstmodule.mednotes.Helper.DepartmentHelper;
import com.firstmodule.mednotes.Helper.RegisterHelper;
import com.firstmodule.mednotes.Model.*;
import com.firstmodule.mednotes.Services.UserServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/systemManagement")
public class SystemManagementModule {
    @Autowired
    private UserServiceInterface us;

    @PostMapping("/login/{email}/{password}")
    public AdminResponse loginUser(@PathVariable String email, @PathVariable String password) {
        Optional<Admin> a = us.loginA(email, password);
        Optional<Doctor> d = us.loginD(email, password);
        Optional<Patient> p = us.loginP(email, password);

        if (a.isPresent()) {
            AdminResponse ar = new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("List e Suksesshme").setData(a).build();
            return ar;
        } else if (d.isPresent()) {
            int roli = d.get().getRole();

            AdminResponse ar = new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Roli:"+roli).setData(d).build();
            return ar;
        } else if (p.isPresent()) {
            int roli = p.get().getRole();
            AdminResponse ar = new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Roli:"+roli).setData(p).build();
            return ar;
        }
         AdminResponse ar = new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This User doesn't exists").build();
        return ar;

    }

    @PostMapping("registerUser")
    public AdminResponse registerU(@RequestBody RegisterHelper rh) {

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
                           return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Welcome to MedNotes").setData(d).build();
                        }
                        return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This email is being used by someone else").build();

                    } else {
                        return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This personalNumber belongs to someone else").build();

                    }
                } else {
                    return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This department doesn't exists").build();

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
                        return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Welcome to MedNotes").setData(p).build();
                    }
                    return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This email is being used by someone else").build();

                } else {
                    return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This personalNumber belongs to someone else").build();

                }
            }
        }
         return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("Please fill all the input fields").build();

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
    public ResponseEntity addDepartment(@RequestBody DepartmentHelper departmentHelper) {
        Optional<Clinic> cc = this.us.finClinById(3);
        List<Department> lista = this.us.findByName(departmentHelper.getDepName());
        if (lista.size() == 0) {
            Department d = new Department(departmentHelper.getDepName(), departmentHelper.getNumberOfRooms(), cc.get());
            this.us.addDepartment(d);
            return ResponseEntity.ok("Department added successfully");
        }
        return ResponseEntity.ok("This Department Exists");
    }

    //error per pk edhe fk
    @PostMapping("/admin/deleteDep/{depName}")
    public ResponseEntity deleteDepartment(@PathVariable String depName) {
        List<Department> dep = this.us.findByName(depName);
        if (dep.size() != 0) {
            this.us.deleteDep(dep.get(0));
            return ResponseEntity.ok("Department deleted successfully!");
        }
        return ResponseEntity.ok("This department doesn't exists!");
    }

    @PostMapping("/admin/deleteAdvert/{advertName}")
    public ResponseEntity deleteAdvert(@PathVariable String advertName) {
     List<Advertisement> l = this.us.getByTitle(advertName);
     if(l.size() != 0){
         this.us.deleteAdvert(l.get(0));
         return ResponseEntity.ok("Advertisement with title"+advertName+"is deleted successfully!");
     }
        return ResponseEntity.ok("Advertisement with title"+advertName+"doesn't exists!");
    }

    @PostMapping("/admin/addAdvert/{advertName}/{aPath}")
    public ResponseEntity addAdvert(@PathVariable String advertName, @PathVariable String aPath) {
      List<Advertisement> l = this.us.getByTitle(advertName);
      if(l.size() != 0){
          return ResponseEntity.ok("This Advertisement already exits!");
      }
      else{
          Admin a = this.us.getByName("Admin");
          Advertisement d = new Advertisement(advertName, aPath , a);
          this.us.addAdvert(d);
          return ResponseEntity.ok("Advertisement added successfully ");
      }

    }
    @PostMapping("/admin/addClinicInfor/{adresa}/{nrTel}/{emaili}/{partnes}")
    public ResponseEntity editClinicInfo(@PathVariable String adresa , @PathVariable String nrTel , @PathVariable String emaili , @PathVariable int partnes){
        this.us.updateClinicsInfos(adresa, nrTel , emaili , partnes);
        return ResponseEntity.ok("Clinic edited successfully");
    }

    @GetMapping("/admin/getClinic")
    public ResponseEntity getClinicInfo(){

        return ResponseEntity.ok(this.us.getAllC());
    }


    @PostMapping("/admin/editDep/{depName}/{numberOfRooms}")
    public ResponseEntity editDepInfo(@PathVariable String depName , @PathVariable int numberOfRooms){
        this.us.editDep(depName , numberOfRooms);
        return ResponseEntity.ok("Department edited successfully!");
    }

}

