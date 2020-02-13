package com.tabibu.backend.api;

import com.tabibu.backend.exceptions.ResourceNotFoundException;
import com.tabibu.backend.models.Death;
import com.tabibu.backend.models.Diagnosis;
import com.tabibu.backend.models.Disease;
import com.tabibu.backend.models.DiseaseDTO;
import com.tabibu.backend.repositories.DiseaseRepository;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
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

@And({
        @Spec(path = "name", spec = Like.class),
        @Spec(path = "description", spec = Like.class)
})
interface DiseaseSpec extends Specification<Disease> {
}

@RestController
@RequestMapping("/api/v1")
public class DiseaseController {

    private final DiseaseRepository repository;

    @Autowired
    public DiseaseController(DiseaseRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/diseases")
    public List<DiseaseDTO> getAllDiseases(DiseaseSpec diseaseSpec) {
        return repository.findAll(diseaseSpec).stream().map(Disease::convertToDTO).collect(Collectors.toList());
    }


    @GetMapping("/diseases/{id}")
    public ResponseEntity<DiseaseDTO> getDiseaseById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Disease disease =
                repository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Disease not found on :: " + id));
        return ResponseEntity.ok().body(disease.convertToDTO());
    }


    @PostMapping("/diseases")
    public DiseaseDTO createDisease(@Valid @RequestBody DiseaseDTO diseaseDTO) {
        Disease disease = diseaseDTO.convertToEntity();
        repository.save(disease);
        return disease.convertToDTO();
    }

    @DeleteMapping("/diseases/{id}")
    public Map<String, Boolean> deleteDisease(@PathVariable(value = "id") Long id) throws Exception {
        Disease disease =
                repository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Disease not found on :: " + id));

        repository.delete(disease);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
