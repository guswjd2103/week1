package com.example.week1_contact;

public class ContactData {
    private String name;
    private String phoneNumber;

    public ContactData(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}
