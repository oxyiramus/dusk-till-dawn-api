package com.dusktilldawn.api.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patrons")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "reservations")
@Getter
@Setter
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
}
