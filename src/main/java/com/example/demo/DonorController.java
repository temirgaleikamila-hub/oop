package com.example.demo;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin(origins = "*")
public class DonorController {

    private final DonorRepository repo;

    public DonorController(DonorRepository repo) {
        this.repo = repo;
    }

    // GET /api/donors?type=Individual OR ?name=Ali
    @GetMapping
    public List<Donor> getAll(
            @RequestParam(required = false, name = "type") String type,
            @RequestParam(required = false, name = "name") String name
    ) {
        if (type != null && !type.isBlank()) return repo.findByDonorTypeIgnoreCase(type.trim());
        if (name != null && !name.isBlank()) return repo.findByFullNameContainingIgnoreCase(name.trim());
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Donor getById(@PathVariable Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Donor not found: id=" + id));
    }

    @PostMapping
    public ResponseEntity<Donor> create(@RequestBody Donor donor) {
        validate(donor, true);
        if (repo.existsById(donor.getDonorId())) {
            throw new ApiException(HttpStatus.CONFLICT, "Donor already exists: id=" + donor.getDonorId());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(donor));
    }

    @PutMapping("/{id}")
    public Donor update(@PathVariable Integer id, @RequestBody Donor donor) {
        validate(donor, false);
        Donor existing = repo.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Donor not found: id=" + id));

        existing.setFullName(donor.getFullName());
        existing.setEmail(donor.getEmail());
        existing.setPhone(donor.getPhone());
        existing.setDonorType(donor.getDonorType());

        return repo.save(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        if (!repo.existsById(id)) throw new ApiException(HttpStatus.NOT_FOUND, "Donor not found: id=" + id);
        repo.deleteById(id);
        return ResponseEntity.ok(Map.of("ok", true, "deletedId", id));
    }

    private void validate(Donor d, boolean requireId) {
        if (d == null) throw new ApiException(HttpStatus.BAD_REQUEST, "Body is empty");
        if (requireId && d.getDonorId() <= 0) throw new ApiException(HttpStatus.BAD_REQUEST, "donorId must be positive");
        if (d.getFullName() == null || d.getFullName().trim().length() < 2)
            throw new ApiException(HttpStatus.BAD_REQUEST, "fullName is required (min 2 chars)");
        if (d.getDonorType() != null && !d.getDonorType().isBlank()) {
            String t = d.getDonorType().trim().toLowerCase();
            if (!t.equals("individual") && !t.equals("corporate")) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "donorType must be Individual or Corporate");
            }
        }
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
