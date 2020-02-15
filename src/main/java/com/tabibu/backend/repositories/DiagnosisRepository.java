package com.tabibu.backend.repositories;

import com.tabibu.backend.models.Diagnosis;
import com.tabibu.backend.models.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long>,
        JpaSpecificationExecutor<Diagnosis> {
    List<Diagnosis> findAllByDiagnosisDateBetweenAndDiseasesContains(Date dateFrom, Date dateTo, Disease disease);

}
