package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Integer> {
    List<Donor> findByDonorTypeIgnoreCase(String donorType);
    List<Donor> findByFullNameContainingIgnoreCase(String fullName);
}
