package com.firstmodule.mednotes.Services;

import com.firstmodule.mednotes.Model.Admin;
import com.firstmodule.mednotes.Model.Doctor;
import com.firstmodule.mednotes.Model.Patient;

public interface UserServiceInterface {
    public Admin loginA(String username , String pass);
    public Doctor loginD(String username , String pass);
    public Patient loginP(String username , String pass);

    public void registerD(Doctor d);
    public void registerP(Patient p);
}
