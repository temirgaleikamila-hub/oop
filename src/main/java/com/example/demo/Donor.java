package com.example.demo;

public class Donor {
    private int donorId;
    private String fullName;
    private String email;
    private String phone;
    private String donorType;

    public Donor() {}

    public Donor(int donorId, String fullName, String email, String phone, String donorType) {
        this.donorId = donorId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.donorType = donorType;
    }

    public int getDonorId() { return donorId; }
    public void setDonorId(int donorId) { this.donorId = donorId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDonorType() { return donorType; }
    public void setDonorType(String donorType) { this.donorType = donorType; }
}
