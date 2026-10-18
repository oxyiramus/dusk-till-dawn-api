package com.dusktilldawn.api.repository;

import com.dusktilldawn.api.model.ShiftTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, Long> {
}