package com.tabibu.backend.repositories;

import com.tabibu.backend.models.Death;
import com.tabibu.backend.models.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DeathRepository extends JpaRepository<Death, Long>, JpaSpecificationExecutor<Death> {
    List<Death> getDeathsByDiseaseAndDeathDateBetween(Disease disease, Date dateFrom, Date dateTo);
}
