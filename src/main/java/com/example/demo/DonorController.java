package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donors")
public class DonorController {

    private final DonorRepository donorRepo;

    public DonorController(DonorRepository donorRepo) {
        this.donorRepo = donorRepo;
    }

    @PostMapping
    public Donor createDonor(@RequestBody Donor donor) {
        return donorRepo.save(donor);
    }

    @GetMapping
    public List<Donor> getAllDonors() {
        return donorRepo.findAll();
    }

    @GetMapping("/{id}")
    public Donor getDonorById(@PathVariable int id) {
        return donorRepo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Donor updateDonor(
            @PathVariable int id,
            @RequestBody Donor updatedDonor
    ) {
        Donor donor = donorRepo.findById(id).orElse(null);
        if (donor == null) return null;

        donor.setFullName(updatedDonor.getFullName());
        donor.setEmail(updatedDonor.getEmail());
        donor.setPhone(updatedDonor.getPhone());
        donor.setDonorType(updatedDonor.getDonorType());

        return donorRepo.save(donor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonor(@PathVariable int id) {
        if (!donorRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        donorRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public List<Donor> searchByName(@RequestParam String name) {
        return donorRepo.findByFullNameContainingIgnoreCase(name);
    }

}
