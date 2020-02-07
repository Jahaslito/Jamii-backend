package com.tabibu.backend.repositories;

import com.tabibu.backend.models.Death;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeathRepository extends JpaRepository<Death, Long> {
}
