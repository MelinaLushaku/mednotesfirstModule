package com.firstmodule.mednotes.Helper;

import java.io.Serializable;

public class AdminResponse<T> implements Serializable {
    private T data;
    private String mesazhi;
    private int statusi;
    private String errori;

    public AdminResponse(AdminResponseBuilder adminResponseBuilder){
        this.data=(T)adminResponseBuilder.data;
        this.mesazhi=adminResponseBuilder.mesazhi;
        this.statusi=adminResponseBuilder.statusi;
        this.errori=adminResponseBuilder.errori;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMesazhi() {
        return mesazhi;
    }

    public void setMesazhi(String mesazhi) {
        this.mesazhi = mesazhi;
    }

    public int getStatusi() {
        return statusi;
    }

    public void setStatusi(int statusi) {
        this.statusi = statusi;
    }

    public String getErrori() {
        return errori;
    }

    public void setErrori(String errori) {
        this.errori = errori;
    }

    public static class AdminResponseBuilder<T>{
        private T data;
        private String mesazhi;
        private int statusi;
        private String errori;
        public AdminResponseBuilder(int statusi){
            this.statusi = statusi;
        }
        public AdminResponseBuilder setData(T data){
            this.data = data;
            return this;
        }
        public AdminResponseBuilder setMesazhin(String mesazhi){
            this.mesazhi = mesazhi;
            return this;
        }
        public AdminResponseBuilder setErrorin(String errori){
            this.errori = errori;
            return this;
        }
        public AdminResponse build(){
            return new AdminResponse (this);
        }

    }

}
