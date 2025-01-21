package com.example.java_project.repository;

import com.example.java_project.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamsRepository extends JpaRepository<Team, Integer> {
    Optional<Team> findById(int id);
}
