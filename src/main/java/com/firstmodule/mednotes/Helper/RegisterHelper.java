package com.firstmodule.mednotes.Helper;

//import com.sun.org.apache.regexp.internal.RE;

import java.io.Serializable;

public class RegisterHelper implements Serializable {

    //sdi a bon qishtu*
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int role;
    private int personalNumber;


    //opsionale veq per mjekun
    private int dep_id;
    private String departmentD;
    private String specializationD;

   public RegisterHelper(){}
    public RegisterHelper(String name, String surname, String email, String password, int role, int personalNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.personalNumber = personalNumber;


    }


    public RegisterHelper(String name, String surname, String email, String password, int role, int personalNumber, int dep_id, String departmentD, String specializationD) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.personalNumber = personalNumber;

        this.dep_id = dep_id;
        this.departmentD = departmentD;
        this.specializationD = specializationD;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }



    public int getDep_id() {
        return dep_id;
    }

    public String getDepartmentD() {
        return departmentD;
    }

    public String getSpecializationD() {
        return specializationD;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }



    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public void setDepartmentD(String departmentD) {
        this.departmentD = departmentD;
    }

    public void setSpecializationD(String specializationD) {
        this.specializationD = specializationD;
    }
}
