package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donors")
public class DonorController {
    private final DonorRepository repo;

    public DonorController(DonorRepository repo) {
        this.repo = repo;
    }

    // GET /donors -> JSON array from DB
    @GetMapping
    public List<Donor> getAll() {
        return repo.findAll();
    }

    // POST /donors -> accepts JSON and inserts into DB
    @PostMapping
    public Donor create(@RequestBody Donor donor) {
        repo.insert(donor);
        return donor;
    }
}
