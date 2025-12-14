package com.example.courseWork5REST.Repositories;

import com.example.courseWork5REST.Models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Integer> {
    Optional<Staff> findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByEmail(String login);
}
