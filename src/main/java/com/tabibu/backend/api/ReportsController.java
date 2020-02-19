package com.tabibu.backend.api;

import com.tabibu.backend.exceptions.ResourceNotFoundException;
import com.tabibu.backend.models.*;
import com.tabibu.backend.repositories.DeathRepository;
import com.tabibu.backend.repositories.DiagnosisRepository;
import com.tabibu.backend.repositories.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ReportsController {
    private final DiseaseRepository diseaseRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final DeathRepository deathRepository;

    @Autowired
    public ReportsController(DiseaseRepository diseaseRepository,
                             DiagnosisRepository diagnosisRepository,
                             DeathRepository deathRepository) {
        this.diseaseRepository = diseaseRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.deathRepository = deathRepository;
    }

    @GetMapping("/reports")
    public ResponseEntity<ReportDTO> getAllReports(@RequestParam int year, @RequestParam Long diseaseId) throws ResourceNotFoundException, ParseException {
       Disease disease = diseaseRepository.findById(diseaseId)
               .orElseThrow(() -> new ResourceNotFoundException("Could not find disease with id: " + diseaseId));

        Date dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-01-01");
        Date dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-12-31");

        List<Diagnosis> diagnoses = diagnosisRepository.findAllByDiagnosisDateBetweenAndDiseasesContains(dateFrom, dateTo, disease);
        List<Death> deaths = deathRepository.getDeathsByDiseaseAndDeathDateBetween(disease, dateFrom, dateTo);

        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setYear(year);
        reportDTO.setDisease(disease.convertToDTO());
        reportDTO.setTotalReportedDeaths(deaths.size());

        List<MonthCasesDTO> monthCases = new ArrayList<>();
        for(int i = 1; i <= 12; i++){
            int monthOfYear = i;
            int totalCases = (int) diagnoses.stream()
                    .filter(diagnosis -> diagnosis.getDiagnosisDate().getMonth() + 1 == monthOfYear).count();
            monthCases.add(new MonthCasesDTO(Month.of(monthOfYear).name(), totalCases));
        }
        reportDTO.setCases(monthCases);

        return ResponseEntity.ok().body(reportDTO);
    }
}
