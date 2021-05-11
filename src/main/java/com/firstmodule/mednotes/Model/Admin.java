package com.firstmodule.mednotes.Model;

import javax.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int adminID;

    @Column
    private String adminName;

    @Column
    private String email;

    @Column
    private String password;
    public Admin (){}
    public Admin(int adminID, String adminName, String email, String password) {
        this.adminID = adminID;
        this.adminName = adminName;
        this.email = email;
        this.password = password;
    }

    public int getAdminID() {
        return adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
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
}
