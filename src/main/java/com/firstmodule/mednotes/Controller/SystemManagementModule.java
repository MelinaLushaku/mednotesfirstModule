package com.firstmodule.mednotes.Controller;

import com.firstmodule.mednotes.Helper.AdminResponse;
import com.firstmodule.mednotes.Helper.DepartmentHelper;
import com.firstmodule.mednotes.Helper.RegisterHelper;
import com.firstmodule.mednotes.Model.*;
import com.firstmodule.mednotes.Services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import javax.print.Doc;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9000/", maxAge = 3600)
@RestController
@RequestMapping("/api/systemManagement")
public class SystemManagementModule {
    @Autowired
    private UserServiceInterface us;
//done
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
//done
    @PostMapping("/registerUser")
    public AdminResponse registerU(@RequestBody RegisterHelper rh) {

        if (!rh.getName().isEmpty() && !rh.getSurname().isEmpty() && !rh.getEmail().isEmpty() && !rh.getPassword().isEmpty() && rh.getPersonalNumber() != 0) {
            if (rh.getRole() == 1 && !rh.getSpecializationD().isEmpty() && !rh.getDepartmentD().isEmpty()) {
                List<Department> dep = this.us.findByName(rh.getDepartmentD());
               Optional<Clinic> c = this.us.getClinicByName("MedNotes");

                if (dep.size() != 0) {
                    List<Doctor> listt = this.us.findDByPN(rh.getPersonalNumber());
                    List<Patient> lista = this.us.findPByPN(rh.getPersonalNumber());
                    if (listt.size() == 0 && lista.size() == 0) {
                        List<Doctor> lista2 = this.us.findDoctorByEmail(rh.getEmail());
                        List<Patient> lista3 = this.us.findPatientByEmail(rh.getEmail());
                        if (lista2.size() == 0 && lista3.size() == 0) {
                            Doctor d = new Doctor(rh.getName(), rh.getSurname(), rh.getSpecializationD(), rh.getDepartmentD(), rh.getEmail(), rh.getPassword(), dep.get(0), c.get(), rh.getPersonalNumber(), rh.getRole());
                            this.us.registerD(d);
                           return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Roli:1").setData(d).build();
                        }
                        return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This email is being used by someone else").build();

                    } else {
                        return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This personalNumber belongs to someone else").build();

                    }
                } else {
                    return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This department doesn't exists").build();

                }
            } else if (rh.getRole() == 2) {
                Optional<Clinic> c = this.us.getClinicByName("MedNotes");

                List<Patient> lista = this.us.findPByPN(rh.getPersonalNumber());
                List<Doctor> listt = this.us.findDByPN(rh.getPersonalNumber());
                if (lista.size() == 0 && listt.size() == 0) {
                    List<Patient> lista2 = this.us.findPatientByEmail(rh.getEmail());
                    List<Doctor> lista3 = this.us.findDoctorByEmail(rh.getEmail());
                    if (lista2.size() == 0 && lista3.size() == 0) {
                        Patient p = new Patient.PatientBuilder(rh.getName(), rh.getSurname(), rh.getEmail(), rh.getPersonalNumber(), rh.getPassword(), rh.getRole(), c.get()).setbloodG("NotSpecified").setHeight(0).setWeight(0).build();
                        this.us.registerP(p);
                        return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Roli:2").setData(p).build();
                    }
                    return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This email is being used by someone else").build();

                } else {
                    return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This personalNumber belongs to someone else").build();

                }
            }
        }
         return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("Please fill all the input fields").build();

    }
//done
    @PostMapping("/admin/deleteUser/{personalNumber}")
    public AdminResponse deleteUser(@PathVariable int personalNumber) {
        List<Doctor> doc = this.us.findDByPN(personalNumber);
        List<Patient> pat = this.us.findPByPN(personalNumber);
        if (doc.size() == 0 && pat.size() == 0) {
            return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("There is no user with this personal number!").build();

        } else if (doc.size() != 0) {
            Doctor d = doc.get(0);
            this.us.deleteDoctor(d);
            return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Doctor with personalNumber:" + personalNumber + "is deleted").build();

        } else {
            Patient p = pat.get(0);
            this.us.deletePatient(p);
            return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Patient with personalNumber:" + personalNumber + "is deleted").build();
        }

    }
//done
    @GetMapping("/admin/totalNumberOfDoc")
    public AdminResponse totalDoc() {
        int totalD = this.us.getTotalDoctor();
         return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Vlere e Suksesshme").setData(totalD).build();
    }
//done
    @GetMapping("/admin/totalNumberOfPat")
    public AdminResponse totalPat() {
        int totalP = this.us.getTotalPatient();
       return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Vlere e sukseshme").setData(totalP).build();
    }
//done
    @GetMapping("/admin/totalNumberOfUser")
    public AdminResponse totalUsers() {
        int totalD = this.us.getTotalDoctor();
        int totalP = this.us.getTotalPatient();
        int totalU = totalD + totalP;
        return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Vlere e sukseshme").setData(totalU).build();
    }
//done
    @GetMapping("/admin/totalNumberOfDep")
    public AdminResponse totalDep() {
        int totalDep = this.us.getTotalDep();
        return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Vlere e sukseshme").setData(totalDep).build();
    }

//done
    @GetMapping("/admin/ListOfDep")
    public AdminResponse listOfDep() {
        List<Department> list = this.us.findAllDep();
        if (list.size() == 0) {
            return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("There is no departments!").build();
        } else {
            return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("List e sukseshme").setData(list).build();
        }
    }
//done
    @PostMapping("/admin/addDepartmentt")
    public AdminResponse addDepartment(@RequestBody DepartmentHelper departmentHelper) {

        Optional<Clinic> cc = this.us.getClinicByName("MedNotes");
        List<Department> lista = this.us.findByName(departmentHelper.getDepName());
        if (lista.size() == 0) {
            Department d = new Department(departmentHelper.getDepName(), departmentHelper.getNumberOfRooms(), cc.get());
            this.us.addDepartment(d);
            return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Department added successfully").setData(d).build();

        }else if(departmentHelper.getDepName() == null){
            return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("Please fill all the inputs").build();
        }
        return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This Department Exists").build();

    }
//done
    @PostMapping("/admin/deleteDep/{depName}")
    public AdminResponse deleteDepartment(@PathVariable String depName) {
        List<Department> dep = this.us.findByName(depName);
        if (dep.size() != 0) {
            this.us.deleteDep(dep.get(0));
            return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Department deleted successfully!").build();

        }
        return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This department doesn't exists!").build();

    }
//done
    @PostMapping("/admin/deleteAdvert/{advertName}")
    public AdminResponse deleteAdvert(@PathVariable String advertName) {
     List<Advertisement> l = this.us.getByTitle(advertName);
     if(l.size() != 0){
         this.us.deleteAdvert(l.get(0));
         return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Advertisement with title "+advertName+" is deleted successfully!").build();

     }
        return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("Advertisement with title"+advertName+"doesn't exists!").build();

    }
//done
    @PostMapping("/admin/addAdvert/{advertName}/{aPath}")
    public AdminResponse addAdvert(@PathVariable String advertName, @PathVariable String aPath) {
      List<Advertisement> l = this.us.getByTitle(advertName);
      if(l.size() != 0){

          return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This Advertisement already exits!").build();

      }
      else{
          Admin a = this.us.getByName("Admin");

          Advertisement d = new Advertisement(advertName, aPath , a);
          this.us.addAdvert(d);
          return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Advertisement added successfully").setData(d).build();


      }

    }
    //done
    @PostMapping("/admin/addClinicInfor/{adresa}/{nrTel}/{emaili}/{partners}")
    public AdminResponse editClinicInfo(@PathVariable String adresa , @PathVariable String nrTel , @PathVariable String emaili , @PathVariable int partners){
        this.us.updateClinicsInfos(adresa, nrTel , emaili , partners);

        return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Clinic edited successfully").build();


    }
//done
    @GetMapping("/admin/getClinic")
    public AdminResponse getClinicInfo(){
         // List<Clinic> c = this.us.getAllC();
          Optional<Clinic> c = this.us.getClinicByName("MedNotes");
          if(c.isPresent()) {

              return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("List e suksseshme").setData(c).build();
          }else {
              return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("Your database doesn't have info for MedNotes Clinic").build();
          }

    }

//done
    @PostMapping("/admin/editDep/{depName}/{numberOfRooms}")
    public AdminResponse editDepInfo(@PathVariable String depName , @PathVariable int numberOfRooms){
        List<Department> dep = this.us.findByName(depName);
        if(dep.size() !=0) {
            this.us.editDep(depName, numberOfRooms);

            return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Department edited successfully!").build();
        }else {
            return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("Department you want do edit doesn't exists!").build();
        }

    }
//done
    @GetMapping("/admin/getAllDep")
    public AdminResponse getAllDep(){
        List<Department> dep = this.us.findAllDep();
        if(dep.size() !=0){
            return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("List e suksseshme").setData(dep).build();
        }else {
            return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("There are no departments!").build();
        }

    }
    //done
    @GetMapping("/admin/getAllAdvert")
    public AdminResponse getAllAdvert(){
        List<Advertisement>adv=this.us.findAllAdvert();
        if(adv.size()!=0){
            return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("List e suksseshme").setData(adv).build();
        }else{
            return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("Lista e zbrazet").build();
        }
    }
    //done
    @GetMapping("/admin/getAllDoctors")
    public AdminResponse getAllDoc(){
        List<Doctor> doc = this.us.findAllD();
        if(doc.size() !=0){
            return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("List e suksseshme").setData(doc).build();
        }else {
            return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("There are no doctors registered!").build();
        }

    }
    //done
    @GetMapping("/admin/getAllPatient")
    public AdminResponse getAllPat(){
        List<Patient> pat = this.us.findAllP();
        if(pat.size() !=0){
            return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("List e suksseshme").setData(pat).build();
        }else {
            return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("There are no patients registered!").build();
        }

    }
    //done
    @GetMapping("/admin/PatientByPersonal/{nrPersonal}")
    public Patient getPatientByPersonal(@PathVariable int nrPersonal){
        List<Patient> lista = this.us.findPByPN(nrPersonal);
        if(lista.size() == 0){
            return null;
        }else{
            return lista.get(0);
        }

    }
    //done
    @GetMapping("/admin/DoctortByPersonal/{doctorPersonalNumber}")
    public Doctor getDoctorByPersonal(@PathVariable int doctorPersonalNumber){
        List<Doctor> lista = this.us.findDByPN(doctorPersonalNumber);
        if(lista.size() == 0){
            return null;
        }else{
            return lista.get(0);
        }

    }
//done
    @GetMapping("/patient/getDoctortByDep/{depNumber}")
    public AdminResponse getDoctorByDep(@PathVariable int depNumber){
        List<Doctor> lista = this.us.getDocByDep(depNumber);
        if(lista.size() == 0){
            return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("there are no doctors in dis department!").build();
        }else{
            return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("list e sukseshsme").setData(lista).build();
        }

    }
    //almost done
    @GetMapping("/doctor/searchDoctor/{doctorName}")
    public AdminResponse searchDoc(@PathVariable String doctorName){
        List<Doctor>lista=this.us.searchDoctorByName(doctorName);
        if(lista.size() == 0){
            return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("there are no doctors !").build();
        }else{
            return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("list e sukseshsme").setData(lista).build();
        }

    }
//done
    @GetMapping("/patient/editProfile/{pId}/{blood}/{w}/{h}")
    public AdminResponse editPat(@PathVariable int pId , @PathVariable String blood , @PathVariable float w , @PathVariable float h){
        List<Patient> pat = this.us.findPByPN(pId);
        if(pat.size() != 0){
            this.us.editPatById(pId, blood, h, w);
            return new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Your profile is edited succesfully!").build();
        }
        return  new AdminResponse.AdminResponseBuilder<>(401).setErrorin("We couldn't ipdate your info! Please try again!").build();
    }
//done
    @PostMapping("/doctor/editP/{docId}/{specialization}")
    public AdminResponse editDoc(@PathVariable int docId , @PathVariable String specialization){
        List<Doctor> lista = this.us.findDByPN(docId);
        if(lista.size() != 0){
            this.us.editDocById(docId, specialization);
            return  new AdminResponse.AdminResponseBuilder<>(201).setMesazhin("Edited succesfully!").build();
        }
        return new AdminResponse.AdminResponseBuilder<>(401).setErrorin("This user doesnt exists!").build();}

}

