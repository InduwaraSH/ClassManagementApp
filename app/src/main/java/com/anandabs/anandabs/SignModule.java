package com.anandabs.anandabs;

public class SignModule {
    String id_num;
    String name_user_last;
    String mob_num;
    String year;
    String password;
    String name_user_first;

    public SignModule() {

    }


    public SignModule(String id_num, String name_user_last, String mob_num, String year, String password,String name_user_first) {
        this.id_num = id_num;
        this.name_user_last = name_user_last;
        this.name_user_first = name_user_first;
        this.mob_num = mob_num;
        this.year = year;
        this.password = password;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getName_user_last() {
        return name_user_last;
    }

    public void setName_user_last(String name_user_last) {
        this.name_user_last = name_user_last;
    }

    public String getName_user_first() {
        return name_user_first;
    }

    public void setName_user_first(String name_user_first) {
        this.name_user_first = name_user_first;
    }

    public String getMob_num() {
        return mob_num;
    }

    public void setMob_num(String mob_num) {
        this.mob_num = mob_num;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
