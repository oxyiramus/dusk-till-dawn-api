package com.dusktilldawn.api.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booths")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "reservations")
@Getter
@Setter
public class Booth {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private int number;

    @Column(nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Zone zone;

    @OneToMany(mappedBy = "booth", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
}
