package com.tabibu.backend.api;

import com.tabibu.backend.exceptions.ResourceNotFoundException;
import com.tabibu.backend.models.Diagnosis;
import com.tabibu.backend.models.Disease;
import com.tabibu.backend.repositories.DiagnosisRepository;
import com.tabibu.backend.repositories.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DiseaseController {

    private final DiseaseRepository repository;

    @Autowired
    public DiseaseController(DiseaseRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/diseases")
    public List<Disease> getAllDiseases() {
        return repository.findAll();
    }


    @GetMapping("/diseases/{id}")
    public ResponseEntity<Disease> getDiseaseById(@PathVariable(value = "id") Long Id)
            throws ResourceNotFoundException {
        Disease disease =
                repository
                        .findById(Id)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + Id));
        return ResponseEntity.ok().body(disease);
    }


    @PostMapping("/diseases")
    public Disease createDisease(@Valid @RequestBody Disease disease) {
        return repository.save(disease);
    }

    Disease diseaseDetails;
    @PutMapping("/diseases/{id}")
    public ResponseEntity<Disease> updateDisease(
            @PathVariable(value = "id") Long Id, @Valid @RequestBody Disease providerDetails)
            throws ResourceNotFoundException {

        Disease disease =
                repository
                        .findById(Id)
                        .orElseThrow(() -> new ResourceNotFoundException("Provider not found on :: " + Id));

        disease.setId(diseaseDetails.getId());
        disease.setName(diseaseDetails.getName());
        disease.setDiagnoses(diseaseDetails.getDiagnoses());
        disease.setDeaths(diseaseDetails.getDeaths());
        disease.setDescription(diseaseDetails.getDescription());
        final Disease updatedDisease = repository.save(providerDetails);
        return ResponseEntity.ok(updatedDisease);
    }


    @DeleteMapping("/diseases/{id}")
    public Map<String, Boolean> deleteDiseas(@PathVariable(value = "id") Long Id) throws Exception {
        Disease disease =
                repository
                        .findById(Id)
                        .orElseThrow(() -> new ResourceNotFoundException("Provider not found on :: " + Id));

        repository.delete(disease);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
