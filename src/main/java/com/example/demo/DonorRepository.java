package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DonorRepository {
    private final JdbcTemplate jdbc;

    public DonorRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Donor> mapper = (rs, rowNum) -> new Donor(
            rs.getInt("donor_id"),
            rs.getString("full_name"),
            rs.getString("email"),
            rs.getString("phone"),
            rs.getString("donor_type")
    );

    public List<Donor> findAll() {
        return jdbc.query(
                "SELECT donor_id, full_name, email, phone, donor_type FROM \"Donor\" ORDER BY donor_id",
                mapper
        );
    }

    public void insert(Donor d) {
        jdbc.update(
                "INSERT INTO \"Donor\" (donor_id, full_name, email, phone, donor_type) VALUES (?, ?, ?, ?, ?)",
                d.getDonorId(), d.getFullName(), d.getEmail(), d.getPhone(), d.getDonorType()
        );
    }
}
