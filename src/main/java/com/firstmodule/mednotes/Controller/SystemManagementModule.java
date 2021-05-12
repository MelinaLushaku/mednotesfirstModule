package com.firstmodule.mednotes.Controller;

import com.firstmodule.mednotes.Model.Admin;
import com.firstmodule.mednotes.Model.Doctor;
import com.firstmodule.mednotes.Model.Patient;
import com.firstmodule.mednotes.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
