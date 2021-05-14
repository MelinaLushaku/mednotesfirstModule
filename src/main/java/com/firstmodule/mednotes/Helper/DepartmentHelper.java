package com.firstmodule.mednotes.Helper;

import java.io.Serializable;

public class DepartmentHelper implements Serializable {
    private String depName;
    private int numberOfRooms;

    public DepartmentHelper(){}

    public DepartmentHelper(String depName, int numberOfRooms) {
        this.depName = depName;
        this.numberOfRooms = numberOfRooms;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
}
