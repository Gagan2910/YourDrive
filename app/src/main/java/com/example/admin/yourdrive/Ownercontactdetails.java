package com.example.admin.yourdrive;

public class Ownercontactdetails {
    private String Carmodel;
    private String Ownerlocation;
    private String Ownercontactno;
    private String Profilepicurl;

    public Ownercontactdetails(String carmodel, String ownerlocation, String ownercontactno, String profilepicurl) {
        Carmodel = carmodel;
        Ownerlocation = ownerlocation;
        Ownercontactno = ownercontactno;
        Profilepicurl = profilepicurl;
    }

    public String getCarmodel() {
        return Carmodel;
    }

    public String getOwnerlocation() {
        return Ownerlocation;
    }

    public String getOwnercontactno() {
        return Ownercontactno;
    }

    public String getProfilepicurl() {
        return Profilepicurl;
    }
}
