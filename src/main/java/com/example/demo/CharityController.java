package com.example.demo;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/charities")
@CrossOrigin(origins = "*")
public class CharityController {

    private final CharityRepository repo;

    public CharityController(CharityRepository repo) {
        this.repo = repo;
    }

    // GET /api/charities?name=help OR ?category=kids
    @GetMapping
    public List<Charity> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category
    ) {
        if (name != null && !name.isBlank()) return repo.findByNameContainingIgnoreCase(name.trim());
        if (category != null && !category.isBlank()) return repo.findByCategoryContainingIgnoreCase(category.trim());
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Charity getById(@PathVariable Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Charity not found: id=" + id));
    }

    @PostMapping
    public ResponseEntity<Charity> create(@RequestBody Charity c) {
        validate(c, true);
        if (repo.existsById(c.getCharityId())) {
            throw new ApiException(HttpStatus.CONFLICT, "Charity already exists: id=" + c.getCharityId());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(c));
    }

    @PutMapping("/{id}")
    public Charity update(@PathVariable Integer id, @RequestBody Charity c) {
        validate(c, false);
        Charity existing = repo.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Charity not found: id=" + id));

        existing.setName(c.getName());
        existing.setCategory(c.getCategory());
        existing.setEmail(c.getEmail());

        return repo.save(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        if (!repo.existsById(id)) throw new ApiException(HttpStatus.NOT_FOUND, "Charity not found: id=" + id);
        repo.deleteById(id);
        return ResponseEntity.ok(Map.of("ok", true, "deletedId", id));
    }

    private void validate(Charity c, boolean requireId) {
        if (c == null) throw new ApiException(HttpStatus.BAD_REQUEST, "Body is empty");
        if (requireId && c.getCharityId() <= 0) throw new ApiException(HttpStatus.BAD_REQUEST, "charityId must be positive");
        if (c.getName() == null || c.getName().trim().length() < 2)
            throw new ApiException(HttpStatus.BAD_REQUEST, "name is required (min 2 chars)");
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
