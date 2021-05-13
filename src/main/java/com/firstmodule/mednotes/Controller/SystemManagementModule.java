package com.firstmodule.mednotes.Controller;

import com.firstmodule.mednotes.Helper.RegisterHelper;
import com.firstmodule.mednotes.Model.*;
import com.firstmodule.mednotes.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/systemManagement")
public class SystemManagementModule {
    @Autowired
    private UserServices us;

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity loginUser(@PathVariable String email , @PathVariable String password){
        Admin a = us.loginA(email , password);
        Doctor d = us.loginD(email,password);
        Patient p = us.loginP(email, password);
        if(a != null){
            return  ResponseEntity.ok(a);
        }else if (d!= null){
            return  ResponseEntity.ok(d);
        }else if (p!= null){
            return ResponseEntity.ok(p);
        }
            return ResponseEntity.ok("This user doesnt exists!");

    }

   @PostMapping("registerUser")
    public ResponseEntity registerU(@RequestBody RegisterHelper rh){

       if(!rh.getName().isEmpty() && !rh.getSurname().isEmpty()  && !rh.getEmail().isEmpty() &&!rh.getPassword().isEmpty() && rh.getPersonalNumber() != 0){
           if (rh.getRole() == 1 && !rh.getSpecializationD().isEmpty() && !rh.getDepartmentD().isEmpty()) {
               Optional<Department> dep = this.us.finDepById(rh.getDep_id());
               Optional<Clinic> c = this.us.finClinById(3);
               if (dep.isPresent()) {
                   List<Doctor> listt = this.us.findDByPN(rh.getPersonalNumber());
                   if(listt.size() == 0){
                       List<Doctor> lista2 = this.us.findDoctorByEmail(rh.getEmail());
                       if(lista2.size() == 0){
                   Doctor d = new Doctor(rh.getName(), rh.getSurname(), rh.getSpecializationD(), rh.getDepartmentD(), rh.getEmail(), rh.getPassword(), dep.get(), c.get(), rh.getPersonalNumber(), rh.getRole());
                   this.us.registerD(d);
                   return ResponseEntity.ok("Welcome to MedNotes");}
                       return ResponseEntity.ok("This email is being used by someone else");}
                   else {
                       return ResponseEntity.ok("This personalNumber belongs to someone else");
                   }
               } else {
                   return ResponseEntity.ok("This department doesn't exists");
               }
           } else if (rh.getRole() == 2) {
               Optional<Clinic> c = this.us.finClinById(3);
               List<Patient> lista = this.us.findPByPN(rh.getPersonalNumber());
               if(lista.size() ==0){
                   List<Patient>lista2 = this.us.findPatientByEmail(rh.getEmail());
                   if(lista2.size() == 0){
               Patient p = new Patient(rh.getName(), rh.getSurname(), rh.getEmail(), rh.getPersonalNumber(), rh.getPassword(), rh.getRole(), c.get());

               this.us.registerP(p);
               return ResponseEntity.ok("Welcome to MedNotes");}
                   return ResponseEntity.ok("This email is being used by someone else");
               }
               else{
                   return ResponseEntity.ok("This personalNumber belongs to someone else");
               }
           }
       }
           return ResponseEntity.ok("Fill all the fields");

    }

}
