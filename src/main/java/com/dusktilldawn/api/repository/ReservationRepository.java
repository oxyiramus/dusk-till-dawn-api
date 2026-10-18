package com.dusktilldawn.api.repository;

import com.dusktilldawn.api.model.Reservation;
import com.dusktilldawn.api.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    List<Reservation> findByPatronId(Long patronId);

    List<Reservation> findByStatus(Status status);

    @Query("SELECT r FROM Reservation r WHERE r.booth.id = :boothId " +
           "AND r.shift.id = :shiftId " +
           "AND r.status IN ('PENDING', 'CONFIRMED')")
    List<Reservation> findActiveByBoothAndShift(
        @Param("boothId") Long boothId, 
        @Param("shiftId") Long shiftId
    );
}