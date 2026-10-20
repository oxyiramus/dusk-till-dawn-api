package com.dusktilldawn.api.model;

import java.time.LocalDate;
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

    @Column(name = "business_date", nullable = false)
    private LocalDate businessDate;

    @Column(name = "start_time", unique = true)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    public Shift(
            LocalDate businessDate,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        this.businessDate = businessDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
