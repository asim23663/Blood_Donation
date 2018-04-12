package com.example.asimyamin.blood_donation.Model;

/**
 * Created by Asim Yamin on 12/7/2017.
 */

public class DonorInfomation {

    private String name,phoneNumber,address,blood,city;

    public DonorInfomation(){

    }
    public DonorInfomation(String name, String phoneNumber, String address, String blood ,String city) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.blood = blood;
        this.city=city;
    }

    public String getCity(){
        return city;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }
}
