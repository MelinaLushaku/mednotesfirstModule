package com.firstmodule.mednotes.Model;

import javax.persistence.*;

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
