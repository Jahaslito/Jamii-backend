package com.tabibu.backend.api;

import com.tabibu.backend.models.ReportDTO;
import com.tabibu.backend.repositories.DiagnosisRepository;
import com.tabibu.backend.repositories.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReportsController {
    private final DiseaseRepository diseaseRepository;
    private final DiagnosisRepository diagnosisRepository;

    @Autowired
    public ReportsController(DiseaseRepository diseaseRepository, DiagnosisRepository diagnosisRepository) {
        this.diseaseRepository = diseaseRepository;
        this.diagnosisRepository = diagnosisRepository;
    }

    @GetMapping("/reports")
    public List<ReportDTO> getAllReports() {
        return new ArrayList<>();
    }
}
