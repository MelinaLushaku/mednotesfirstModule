package com.firstmodule.mednotes.Model;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;


@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int aID;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private String aPath;

    @ManyToOne
    @JoinColumn(name="admin")
    public Admin admin;


    public  Advertisement(){}

    public Advertisement(String title, String aPath, Admin admin){

        this.title=title;
        this.aPath=aPath;
        this.admin = admin;
    }
    public  int getAID(){
        return aID;
    }
    public  String getTitle(){
        return title;
    }
    public  String getAPath(){
        return aPath;
    }

    public void setAID(int aID) {
        this.aID = aID;
    }

    public void setAPath(String aPath) {
        this.aPath = aPath;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
