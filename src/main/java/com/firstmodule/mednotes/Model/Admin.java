package com.firstmodule.mednotes.Model;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

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
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
    @JsonIgnore
    private Set<Advertisement> add;
    @JsonIgnore
    public Set<Advertisement> getAdvertisement() {
        return add;
    }
    public void setDoctors(Set<Advertisement> add) {
        this.add = add;
    }

    @Column
    private String password;
    public Admin (){}
    public Admin(int adminID, String adminName, String email, String password, Clinic clinic) {
        this.adminID = adminID;
        this.adminName = adminName;
        this.email = email;
        this.password = password;
        this.clinic = clinic;
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
