package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
    List<Donation> findByDonor_DonorId(int donorId);
    List<Donation> findByCharity_CharityId(int charityId);
}
