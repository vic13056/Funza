package com.example.funza;

public class ItemConstructor {
    private String id_column,name,phone,location,gender,address;

    public ItemConstructor() {
    }

    public ItemConstructor(String id_column, String name, String phone, String location, String gender, String address) {
        this.id_column = id_column;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.gender = gender;
        this.address = address;

    }

    public String getId_column() {
        return id_column;
    }

    public void setId_column(String id_column) {
        this.id_column = id_column;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
