package com.example.java_project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_teams")
public class UserTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_team_id", nullable = false, unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
