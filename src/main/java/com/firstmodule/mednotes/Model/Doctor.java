package com.firstmodule.mednotes.Model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.print.Doc;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int doctorId;

    @Column
    private String doctorName;

    @Column
    private String doctorSurname;

    @Column
    private String doctorSpecialization;

    @Column
    private String department;

    @Column
    private String email;

    @Column
    private String password;
    @Column
    private int role;

    @ManyToOne
    @JoinColumn(name="depId")
    private Department departmentid;

    public Doctor(){}
    public Doctor(int doctorId, String doctorName, String doctorSurname, String doctorSpecialization, String department, String email, String password, Department departmentid) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.doctorSpecialization = doctorSpecialization;
        this.department = department;
        this.email = email;
        this.password = password;
        this.departmentid = departmentid;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
