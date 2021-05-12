package com.firstmodule.mednotes.Model;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int depId;

    @Column(nullable = false)
    private String depName;

    @Column(nullable = false)
    private int numOfRooms;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Doctor> doctor;
    @JsonIgnore
    public Set<Doctor> getDoc() {
        return doctor;
    }
    @JsonIgnore
    public void setDoctors(Set<Doctor> doctor) {
        this.doctor = doctor;
    }


    public Department(){}

    public Department (int depId,String depName, int numOfRooms ){
      depId=depId;
        this.depName=depName;
        this.numOfRooms=numOfRooms;
    }


    public int getDepId() {
        return depId;
    }

    public String getDepName() {
        return depName;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }
}