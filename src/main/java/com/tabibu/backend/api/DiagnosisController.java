package com.tabibu.backend.api;

import com.tabibu.backend.exceptions.ResourceNotFoundException;
import com.tabibu.backend.models.Diagnosis;
import com.tabibu.backend.models.DiagnosisDTO;
import com.tabibu.backend.models.Disease;
import com.tabibu.backend.models.DiseaseDTO;
import com.tabibu.backend.repositories.DiagnosisRepository;
import com.tabibu.backend.repositories.DiseaseRepository;
import com.tabibu.backend.repositories.HealthCareProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class DiagnosisController {

        private final DiagnosisRepository diagnosisRepository;
        private final HealthCareProviderRepository healthCareProviderRepository;
        private final DiseaseRepository diseaseRepository;

        @Autowired
        public DiagnosisController(DiagnosisRepository diagnosisRepository,
                                   HealthCareProviderRepository healthCareProviderRepository,
                                   DiseaseRepository diseaseRepository) {
            this.diagnosisRepository = diagnosisRepository;
            this.healthCareProviderRepository = healthCareProviderRepository;
            this.diseaseRepository = diseaseRepository;
        }


        @GetMapping("/diagnosis")
        public List<DiagnosisDTO> getAllDiagnosis(DiagnosisSpec diagnosisSpec) {
            return diagnosisRepository.findAll(diagnosisSpec).stream().map(Diagnosis::convertToDTO).collect(Collectors.toList());
        }


        @GetMapping("/diagnosis/{id}")
        public ResponseEntity<DiagnosisDTO> getDiagnosisById(@PathVariable(value = "id") Long Id)
                throws ResourceNotFoundException {
            Diagnosis diagnosis =
                    diagnosisRepository
                            .findById(Id)
                            .orElseThrow(() -> new ResourceNotFoundException("Diagnosis not found on :: " + Id));
            return ResponseEntity.ok().body(diagnosis.convertToDTO());
        }

        // TODO: DiagnosisDTO list of diseases should take Ids and not DiseaseDTOs
        @PostMapping("/diagnosis")
        public DiagnosisDTO createDiagnosis(@Valid @RequestBody DiagnosisDTO diagnosisDTO) throws ResourceNotFoundException, ParseException {
            Diagnosis diagnosis = this.convertToEntity(diagnosisDTO);
            diagnosisRepository.save(diagnosis);
            return diagnosis.convertToDTO();
        }

        @DeleteMapping("/diagnosis/{id}")
        public Map<String, Boolean> deleteDiagnosis(@PathVariable(value = "id") Long Id) throws Exception {
            Diagnosis diagnosis =
                    diagnosisRepository
                            .findById(Id)
                            .orElseThrow(() -> new ResourceNotFoundException("Provider not found on :: " + Id));

            diagnosisRepository.delete(diagnosis);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        }

        private Diagnosis convertToEntity(DiagnosisDTO diagnosisDTO) throws ParseException, ResourceNotFoundException {
            Diagnosis diagnosis = new Diagnosis();
            diagnosis.setHealthCareProvider(healthCareProviderRepository
                    .findById(diagnosisDTO.getHealthCareProvider().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Provider not found on :: " + diagnosisDTO.getHealthCareProvider())));
            diagnosis.setPatientAge(diagnosisDTO.getPatientAge());
            diagnosis.setDiagnosisDate(new SimpleDateFormat("yyyy-MM-dd").parse(diagnosisDTO.getDiagnosisDate()));

            List<Disease> diseases = new ArrayList<>();
            for (DiseaseDTO diseaseDTO : diagnosisDTO.getDiseases()) {
                Disease disease = diseaseRepository.findById(diagnosisDTO.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Disease not found on id: " + diagnosisDTO.getId()));
                diseases.add(disease);
            }
            diagnosis.setDiseases(diseases);
            return diagnosis;
        }
    }