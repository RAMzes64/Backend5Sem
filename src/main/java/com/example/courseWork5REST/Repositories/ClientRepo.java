package com.example.courseWork5REST.Repositories;

import com.example.courseWork5REST.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    Optional<Client> findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByEmail(String login);
}
