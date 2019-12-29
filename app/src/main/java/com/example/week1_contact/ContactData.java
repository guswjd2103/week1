package com.example.week1_contact;

public class ContactData implements  Comparable<ContactData> {
    private int photo;
    private String name;
    private String phoneNumber;

    public ContactData(int photo, String name, String phoneNumber) {
        this.photo = photo;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getPhoto() {
        return this.photo;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public int compareTo(ContactData contactData) {
        return this.name.compareTo(contactData.getName());
    }
}
