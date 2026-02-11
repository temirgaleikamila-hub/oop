package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CharityRepository extends JpaRepository<Charity, Integer> {
    List<Charity> findByNameContainingIgnoreCase(String name);
    List<Charity> findByCategoryContainingIgnoreCase(String category);
}
