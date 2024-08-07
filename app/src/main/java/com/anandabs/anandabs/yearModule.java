package com.anandabs.anandabs;

public class yearModule {
    String name_user;
    String id_num;
    String year;
    String mob_num;
    String firstName;

    public yearModule(){}




    public yearModule(String name_user, String id_num, String year, String mob_num,String firstName) {
        this.name_user = name_user;
        this.id_num = id_num;
        this.year = year;
        this.mob_num = mob_num;
        this.firstName = firstName;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMob_num() {
        return mob_num;
    }

    public void setMob_num(String mob_num) {
        this.mob_num = mob_num;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
