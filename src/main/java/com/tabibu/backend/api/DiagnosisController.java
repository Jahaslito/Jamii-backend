package com.tabibu.backend.api;

import com.tabibu.backend.exceptions.ResourceNotFoundException;
import com.tabibu.backend.models.*;
import com.tabibu.backend.repositories.DiagnosisRepository;
import com.tabibu.backend.repositories.DiseaseRepository;
import com.tabibu.backend.repositories.HealthCareProviderRepository;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Joins;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

@Joins({
        @Join(path= "diseases", alias = "disease")
})
@And({
        @Spec(path="disease.id", params="diseaseId", spec = Equal.class),
        @Spec(path = "healthCareProvider.id", params = "healthCareProviderId", spec = Equal.class),
        @Spec(path = "diagnosisDate", params = "dateFrom", spec = GreaterThanOrEqual.class),
        @Spec(path = "diagnosisDate", params = "dateTo", spec = LessThanOrEqual.class)
})
interface DiagnosisSpec extends Specification<Diagnosis> {
}

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
            diagnosis.setDiagnosisDate(new SimpleDateFormat("dd-MM-yyyy").parse(diagnosisDTO.getDiagnosisDate()));

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