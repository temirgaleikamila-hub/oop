package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Integer> {
    List<Donor> findByFullNameContainingIgnoreCase(String name);
    long count();

}
