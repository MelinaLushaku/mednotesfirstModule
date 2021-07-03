package com.firstmodule.mednotes.Helper;

import java.io.Serializable;

public class AdvertismentHelper implements Serializable {
    private  String title;
    private  String aPath;
    public AdvertismentHelper(){}
    public AdvertismentHelper(String title,String aPath){
        this.title=title;
        this.aPath=aPath;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setaPath(String aPath) {
        this.aPath = aPath;
    }

    public String getTitle() {
        return title;
    }

    public String getaPath() {
        return aPath;
    }
}
