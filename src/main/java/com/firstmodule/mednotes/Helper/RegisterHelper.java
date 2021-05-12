package com.firstmodule.mednotes.Helper;

import com.sun.org.apache.regexp.internal.RE;

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
    private int clinic_id;

    //opsionale veq per mjekun
    private int dep_id;
    private String departmentD;
    private String specializationD;


    public RegisterHelper(RegisterHelperBuilder registerHelperBuilder){
        this.id = registerHelperBuilder.id;
        this.name = registerHelperBuilder.name;
        this.surname = registerHelperBuilder.surname;
        this.email = registerHelperBuilder.email;
        this.role = registerHelperBuilder.role;
        this.clinic_id = registerHelperBuilder.clinic_id;
        this.password = registerHelperBuilder.password;
        this.personalNumber = registerHelperBuilder.personalNumber ;
        this.dep_id = registerHelperBuilder.dep_id;
        this.departmentD = registerHelperBuilder.departmentD;
        this.specializationD = registerHelperBuilder.specializationD;
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

    public int getClinic_id() {
        return clinic_id;
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

    public static class RegisterHelperBuilder{
        private int id;
        private String name;
        private String surname;
        private String email;
        private String password;
        private int role;
        private int personalNumber;
        private int clinic_id;


        private int dep_id;
        private String departmentD;
        private String specializationD;

        public RegisterHelperBuilder(String name,String surname,String email, String password, int role, int personalNumber, int clinic_id ){
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.role = role;
            this.clinic_id = clinic_id;
            this.password = password;
            this.personalNumber = personalNumber;

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



        public void setClinic_id(int clinic_id) {
            this.clinic_id = clinic_id;
        }



        public void setId(int id) {
            this.id = id;
        }



        public void setDep_id(int dep_id) {
            this.dep_id = dep_id;
        }



        public void setDepartmentD(String departmentD) {
            this.departmentD = departmentD;
        }



       public RegisterHelper build(){
            return new RegisterHelper(this);
       }
    }





}
