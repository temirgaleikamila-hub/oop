package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @Column(name = "donation_id")
    private int donationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "donor_id")     // FK column in donations
    private Donor donor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "charity_id")   // FK column in donations
    private Charity charity;

    @Column(name = "amount")
    private double amount;

    @Column(name = "donated_at")
    private String donatedAt;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "status")
    private String status;

    public Donation() {}

    public int getDonationId() { return donationId; }
    public void setDonationId(int donationId) { this.donationId = donationId; }

    public Donor getDonor() { return donor; }
    public void setDonor(Donor donor) { this.donor = donor; }

    public Charity getCharity() { return charity; }
    public void setCharity(Charity charity) { this.charity = charity; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getDonatedAt() { return donatedAt; }
    public void setDonatedAt(String donatedAt) { this.donatedAt = donatedAt; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
