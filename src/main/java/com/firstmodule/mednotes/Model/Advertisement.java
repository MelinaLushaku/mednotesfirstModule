package com.firstmodule.mednotes.Model;
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

    public  Advertisement(){}

    public Advertisement(int aID, String title, String aPath){
        this.aID=aID;
        this.title=title;
        this.aPath=aPath;
    }
    public  int getaID(){
        return aID;
    }
    public  String getTitle(){
        return title;
    }
    public  String getAPath(){
        return aPath;
    }

    public void setaID(int aID) {
        this.aID = aID;
    }

    public void setaPath(String aPath) {
        this.aPath = aPath;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
