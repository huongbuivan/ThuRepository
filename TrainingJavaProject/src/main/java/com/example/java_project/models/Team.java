package com.example.java_project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id", nullable = false, unique = true)
    private int id;

    @Column(name = "team_name", nullable = false, unique = true)
    private String teamName;

    @Column(name = "description")
    private String description;
}
