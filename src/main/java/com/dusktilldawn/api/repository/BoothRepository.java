package com.dusktilldawn.api.repository;

import com.dusktilldawn.api.model.Booth;
import com.dusktilldawn.api.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoothRepository extends JpaRepository<Booth, Long> {
    
    List<Booth> findByZone(Zone zone);

    List<Booth> findByCapacityGreaterThanEqual(int guests);
}