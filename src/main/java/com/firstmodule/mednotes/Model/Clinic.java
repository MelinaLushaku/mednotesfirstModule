package com.firstmodule.mednotes.Model;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  int cID;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String adrres;

    @Column(nullable=false)
    private String phone;

    @Column(nullable=false)
    private  String email;

    @Column
    private int partners;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinic")
    private Set<Doctor> doc;
    public Set<Doctor> getDoc() {
        return doc;
    }
    public void setDoctors(Set<Doctor> doc) {
        this.doc = doc;
    }
    @JsonIgnore
    @OneToOne(mappedBy = "clinic")
    private Admin admin;

@Column
    private int patients;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinic")
    @JsonIgnore
    private Set<Patient> pat;
    @JsonIgnore
    public Set<Patient> getPat() {
        return pat;
    }
    @JsonIgnore
    public void setPatients(Set<Patient> pat) {
        this.pat = pat;
    }

    @Column
    private int departments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinic")
    @JsonIgnore
    private Set<Department> dep;
    @JsonIgnore
    public Set<Department> getDep() {
        return dep;
    }
    @JsonIgnore
    public void setDepartments(Set<Department> dep) {
        this.dep = dep;
    }

    public  Clinic(){}




    public Clinic(int cID,String name,String adress, String phone, String email, int partners,int departments,int patients){
        this.cID=cID;
        this.name=name;
        this.adrres=adress;
        this.phone=phone;
        this.email=email;
        this.partners=partners;
        this.departments=departments;
        this.patients=patients;





    }
    public  int getcID(){
        return cID;
    }
    public  String getName(){
        return name;
    }

    public  String getAdrres(){
        return adrres;
    }

    public  String getPhone(){
        return  phone;
    }
    public  String getEmail(){
        return  email;

    }
    public  int getPartners(){
        return  partners;
    }

    public int getDepartments() {
        return departments;
    }

  public int getPatients() {
        return patients;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdrres(String adrres) {
        this.adrres = adrres;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPartners(int partners) {
        this.partners = partners;
    }

    public void setPatients(int patients) {
        this.patients = patients;
    }

    public void setDepartments(int departments) {
        this.departments = departments;
    }
}
