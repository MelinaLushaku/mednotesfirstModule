package com.firstmodule.mednotes.Model;



import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
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

    @Column(nullable=true)
    private String bloodG;

    @Column(nullable=true)
    private float height;

    @Column(nullable=true)
    private float weight;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="clinicId")
    private Clinic clinic;



    public Patient(){}

    public Patient (PatientBuilder patientBuilder){
       this.name=patientBuilder.name;
       this.surname=patientBuilder.surname;
       this.email=patientBuilder.email;
       this.personalNumber=patientBuilder.personalNumber;
       this.password=patientBuilder.password;
       this.role=patientBuilder.role;
       this.clinic=patientBuilder.clinic;
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

    public String getBloodG() {
        return bloodG;
    }

    public void setBloodG(String bloodG) {
        this.bloodG = bloodG;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
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

    public  static  class  PatientBuilder{

        private String name;
        private String surname;
        private String email;
        private int personalNumber;
        private String password;
        private int role;
        private String bloodG;
        private float height;
        private float weight;
        @JsonIgnore
        private Clinic clinic;

        public PatientBuilder(String name, String surname,String email,int personalNumber,String password,int role, Clinic clinic){
            this.name=name;
            this.surname=surname;
            this.email=email;
            this.personalNumber=personalNumber;
            this.password=password;
            this.role=role;
            this.clinic=clinic;
        }

        public PatientBuilder setbloodG(String bloodG){
            this.bloodG = bloodG;
            return this;
        }
        public PatientBuilder setHeight(float height){
            this.height = height;
            return this;
        }
        public PatientBuilder setWeight(float weight){
            this.weight = weight;
            return this;
        }
        public Patient build(){
            return new Patient(this);
        }

    }
}
