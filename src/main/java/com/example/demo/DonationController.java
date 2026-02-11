package com.example.demo;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = "*")
public class DonationController {

    private final DonationRepository donationRepo;
    private final DonorRepository donorRepo;
    private final CharityRepository charityRepo;

    public DonationController(DonationRepository donationRepo, DonorRepository donorRepo, CharityRepository charityRepo) {
        this.donationRepo = donationRepo;
        this.donorRepo = donorRepo;
        this.charityRepo = charityRepo;
    }

    public static class DonationRequest {
        public Integer donationId;
        public Integer donorId;
        public Integer charityId;
        public Double amount;
        public String donatedAt;
        public String paymentMethod;
        public String status;
    }

    // GET /api/donations?donorId=1 OR ?charityId=2
    @GetMapping
    public List<Donation> getAll(
            @RequestParam(required = false) Integer donorId,
            @RequestParam(required = false) Integer charityId
    ) {
        if (donorId != null) return donationRepo.findByDonor_DonorId(donorId);
        if (charityId != null) return donationRepo.findByCharity_CharityId(charityId);
        return donationRepo.findAll();
    }

    @GetMapping("/{id}")
    public Donation getById(@PathVariable Integer id) {
        return donationRepo.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Donation not found: id=" + id));
    }

    @PostMapping
    public ResponseEntity<Donation> create(@RequestBody DonationRequest req) {
        validate(req, true);

        if (donationRepo.existsById(req.donationId)) {
            throw new ApiException(HttpStatus.CONFLICT, "Donation already exists: id=" + req.donationId);
        }

        Donor donor = donorRepo.findById(req.donorId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Donor not found: id=" + req.donorId));

        Charity charity = charityRepo.findById(req.charityId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Charity not found: id=" + req.charityId));

        Donation d = new Donation();
        d.setDonationId(req.donationId);
        d.setDonor(donor);
        d.setCharity(charity);
        d.setAmount(req.amount);
        d.setDonatedAt(req.donatedAt);
        d.setPaymentMethod(req.paymentMethod);
        d.setStatus(req.status);

        return ResponseEntity.status(HttpStatus.CREATED).body(donationRepo.save(d));
    }

    @PutMapping("/{id}")
    public Donation update(@PathVariable Integer id, @RequestBody DonationRequest req) {
        validate(req, false);

        Donation existing = donationRepo.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Donation not found: id=" + id));

        // donor/charity можно менять
        if (req.donorId != null) {
            Donor donor = donorRepo.findById(req.donorId)
                    .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Donor not found: id=" + req.donorId));
            existing.setDonor(donor);
        }
        if (req.charityId != null) {
            Charity charity = charityRepo.findById(req.charityId)
                    .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Charity not found: id=" + req.charityId));
            existing.setCharity(charity);
        }

        existing.setAmount(req.amount);
        existing.setDonatedAt(req.donatedAt);
        existing.setPaymentMethod(req.paymentMethod);
        existing.setStatus(req.status);

        return donationRepo.save(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        if (!donationRepo.existsById(id)) throw new ApiException(HttpStatus.NOT_FOUND, "Donation not found: id=" + id);
        donationRepo.deleteById(id);
        return ResponseEntity.ok(Map.of("ok", true, "deletedId", id));
    }

    private void validate(DonationRequest req, boolean requireId) {
        if (req == null) throw new ApiException(HttpStatus.BAD_REQUEST, "Body is empty");
        if (requireId) {
            if (req.donationId == null || req.donationId <= 0) throw new ApiException(HttpStatus.BAD_REQUEST, "donationId must be positive");
            if (req.donorId == null || req.donorId <= 0) throw new ApiException(HttpStatus.BAD_REQUEST, "donorId is required");
            if (req.charityId == null || req.charityId <= 0) throw new ApiException(HttpStatus.BAD_REQUEST, "charityId is required");
        }
        if (req.amount == null || req.amount <= 0) throw new ApiException(HttpStatus.BAD_REQUEST, "amount must be > 0");
    }

    public static class ApiException extends RuntimeException {
        private final HttpStatus status;
        public ApiException(HttpStatus status, String message) { super(message); this.status = status; }
        public HttpStatus getStatus() { return status; }
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApi(ApiException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new LinkedHashMap<>(Map.of("status", ex.getStatus().value(), "error", ex.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleOther(Exception ex) {
        return ResponseEntity.status(500)
                .body(new LinkedHashMap<>(Map.of("status", 500, "error", "Server error: " + ex.getMessage())));
    }
}
