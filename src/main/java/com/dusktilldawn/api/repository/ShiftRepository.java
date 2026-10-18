package com.dusktilldawn.api.repository;

import com.dusktilldawn.api.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    
    List<Shift> findByStartTimeAfter(LocalDateTime time);
}