package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "charities")
public class Charity {

    @Id
    @Column(name = "charity_id")
    private int charityId;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "email")
    private String email;

    public Charity() {}

    public Charity(int charityId, String name, String category, String email) {
        this.charityId = charityId;
        this.name = name;
        this.category = category;
        this.email = email;
    }

    public int getCharityId() { return charityId; }
    public void setCharityId(int charityId) { this.charityId = charityId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
