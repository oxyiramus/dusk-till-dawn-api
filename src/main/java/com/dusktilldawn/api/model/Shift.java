package com.dusktilldawn.api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shifts")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "reservations")
@Getter
@Setter
public class Shift {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
}
