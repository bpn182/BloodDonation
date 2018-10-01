package com.example.user.blooddonation;

public class Doners {
    public String id;
    public String name;
    public String phone;
    public String email;
    public String lastDonated;
    public String group;
    public String district;
    public String address;

    public Doners() {
    }

    public Doners(String id, String name, String phone, String email, String lastDonated, String group, String district, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.lastDonated = lastDonated;
        this.group = group;
        this.district = district;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastDonated() {
        return lastDonated;
    }

    public void setLastDonated(String lastDonated) {
        this.lastDonated = lastDonated;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
