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
    private Set<Doctor> doc;
    public Set<Doctor> getDoc() {
        return doc;}
    public void setDoctors(Set<Doctor> doc) {
        this.doc = doc;
    }
    @JsonIgnore
   @ManyToOne
   @JoinColumn(name="cID")
   private Clinic clinic;

    public Department(){}

    public Department (String depName, int numOfRooms,Clinic clinic ){

        this.depName=depName;
        this.numOfRooms=numOfRooms;
        this.clinic=clinic;
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
