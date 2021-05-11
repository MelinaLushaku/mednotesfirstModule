package com.firstmodule.mednotes.Model;
import javax.persistence.*;

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

    public  Clinic(){}




    public Clinic(int cID,String name,String adress, String phone, String email, int partners){
        this.cID=cID;
        this.name=name;
        this.adrres=adress;
        this.phone=phone;
        this.email=email;
        this.partners=partners;




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
}
