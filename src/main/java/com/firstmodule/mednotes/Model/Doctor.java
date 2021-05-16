package com.firstmodule.mednotes.Model;

import javax.persistence.*;
import javax.print.Doc;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int doctorId;
    @Column
    private int personalNumber;

    @Column
    private String doctorName;

    @Column
    private String doctorSurname;

    @Column
    private String doctorSpecialization;

    @Column
    private String departmentN;

    @Column
    private String email;

    @Column
    private String password;
    @Column
    private int role;


    @ManyToOne
    @JoinColumn(name="depId")
    private Department department;

    @ManyToOne
    @JoinColumn(name="clinicId")
    private Clinic clinic;

    public Doctor(){}
    public Doctor(String doctorName, String doctorSurname, String doctorSpecialization, String departmentN, String email, String password, Department department, Clinic clinic , int personalNumber, int role) {

        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.doctorSpecialization = doctorSpecialization;
        this.departmentN = departmentN;
        this.email = email;
        this.password = password;
        this.department = department;
        this.clinic = clinic;
        this.personalNumber = personalNumber;
        this.role = role;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public String getDepartmentN() {
        return departmentN;
    }

    public void setDepartmentN(String departmentN) {
        this.departmentN = departmentN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
