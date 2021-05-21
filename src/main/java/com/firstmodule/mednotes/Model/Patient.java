package com.firstmodule.mednotes.Model;



import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


@Entity

public class Patient {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int pId;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private int personalNumber;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int role;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="clinicId")
    public Clinic clinic;



    public Patient(){}

    public Patient (String name, String surname,String email,int personalNumber,String password,int role, Clinic clinic){
       this.name=name;
       this.surname=surname;
       this.email=email;
       this.personalNumber=personalNumber;
       this.password=password;
       this.role=role;
       this.clinic=clinic;
    }

    public int getpId() {
        return pId;
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

    public int getPersonalNumber() {
        return personalNumber;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
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

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    public void setRole(int role) {
        this.role = role;
    }


}
