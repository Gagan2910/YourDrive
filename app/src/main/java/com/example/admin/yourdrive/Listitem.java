package com.example.admin.yourdrive;

public class Listitem {
    private String Carmodel;
    private String Carlocation;
    private String CarTripcost;
    private String CarImageurl;
    private String Carpickupdate;
    private String Cardropoffdate;
    private String Cardesciption;

    public Listitem(String carmodel, String carlocation, String carTripcost, String carImageurl, String carpickupdate, String cardropoffdate, String cardesciption) {
        Carmodel = carmodel;
        Carlocation = carlocation;
        CarTripcost = carTripcost;
        CarImageurl = carImageurl;
        Carpickupdate = carpickupdate;
        Cardropoffdate = cardropoffdate;
        Cardesciption = cardesciption;
    }

    public String getCarmodel() {
        return Carmodel;
    }

    public String getCarlocation() {
        return Carlocation;
    }

    public String getCarTripcost() {
        return CarTripcost;
    }

    public String getCarImageurl() {
        return CarImageurl;
    }

    public String getCarpickupdate() {
        return Carpickupdate;
    }

    public String getCardropoffdate() {
        return Cardropoffdate;
    }

    public String getCardesciption() {
        return Cardesciption;
    }
}