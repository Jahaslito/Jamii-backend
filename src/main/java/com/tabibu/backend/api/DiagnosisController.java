package com.tabibu.backend.api;

import com.tabibu.backend.exceptions.ResourceNotFoundException;
import com.tabibu.backend.models.Diagnosis;
import com.tabibu.backend.repositories.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DiagnosisController {

        private final DiagnosisRepository repository;

        @Autowired
        public DiagnosisController(DiagnosisRepository repository) {
            this.repository = repository;
        }


        @GetMapping("/diagnosis")
        public List<Diagnosis> getAllDiagnosis() {
            return repository.findAll();
        }


        @GetMapping("/diagnosis/{id}")
        public ResponseEntity<Diagnosis> getDiagnosisById(@PathVariable(value = "id") Long Id)
                throws ResourceNotFoundException {
            Diagnosis provider =
                    repository
                            .findById(Id)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + Id));
            return ResponseEntity.ok().body(provider);
        }


        @PostMapping("/diagnosis")
        public Diagnosis createDiagnosis(@Valid @RequestBody Diagnosis diagnosis) {
            return repository.save(diagnosis);
        }

        Diagnosis diagnosisDetails;
        @PutMapping("/diagnosis/{id}")
        public ResponseEntity<Diagnosis> updateDiagnosis(
                @PathVariable(value = "id") Long Id, @Valid @RequestBody Diagnosis providerDetails)
                throws ResourceNotFoundException {

            Diagnosis diagnosis =
                    repository
                            .findById(Id)
                            .orElseThrow(() -> new ResourceNotFoundException("Provider not found on :: " + Id));

            diagnosis.setId( diagnosisDetails.getId());
            diagnosis.setDiagnosisDate(diagnosisDetails.getDiagnosisDate());
            diagnosis.setDiseases(diagnosisDetails.getDiseases());
            diagnosis.setPatientAge(diagnosisDetails.getPatientAge());
            diagnosis.setHealthCareProvider(diagnosisDetails.getHealthCareProvider());
            final Diagnosis updatedDiagnosis = repository.save(providerDetails);
            return ResponseEntity.ok(updatedDiagnosis);
        }


        @DeleteMapping("/diagnosis/{id}")
        public Map<String, Boolean> deleteDiagnosis(@PathVariable(value = "id") Long Id) throws Exception {
            Diagnosis diagnosis =
                    repository
                            .findById(Id)
                            .orElseThrow(() -> new ResourceNotFoundException("Provider not found on :: " + Id));

            repository.delete(diagnosis);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        }

    }
