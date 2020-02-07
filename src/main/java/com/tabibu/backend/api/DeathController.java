package com.tabibu.backend.api;

import com.tabibu.backend.exceptions.ResourceNotFoundException;
import com.tabibu.backend.models.Death;
import com.tabibu.backend.models.HealthCareProvider;
import com.tabibu.backend.repositories.DeathRepository;
import com.tabibu.backend.repositories.HealthCareProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DeathController {
    private final DeathRepository repository;

    @Autowired
    public DeathController(DeathRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/deaths")
    public List<Death> getAllDeaths() {
        return repository.findAll();
    }


    @GetMapping("/deaths/{id}")
    public ResponseEntity<Death> getDeathsById(@PathVariable(value = "id") Long Id)
            throws ResourceNotFoundException {
        Death provider =
                repository
                        .findById(Id)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + Id));
        return ResponseEntity.ok().body(provider);
    }


    @PostMapping("/deaths")
    public Death createDeath(@Valid @RequestBody Death death) {
        return repository.save(death);
    }

    Death deathDetails;
    @PutMapping("/deaths/{id}")
    public ResponseEntity<Death> updateDeaths(
            @PathVariable(value = "id") Long Id, @Valid @RequestBody Death providerDetails)
            throws ResourceNotFoundException {

        Death death =
                repository
                        .findById(Id)
                        .orElseThrow(() -> new ResourceNotFoundException("Provider not found on :: " + Id));

        death.setCorpseAge( deathDetails.getCorpseAge());
        death.setDeathDate(deathDetails.getDeathDate());
        death.setDisease(deathDetails.getDisease());
        death.setHealthCareProvider(deathDetails.getHealthCareProvider());
        death.setId(deathDetails.getId());
        final Death updatedDeath = repository.save(providerDetails);
        return ResponseEntity.ok(updatedDeath);
    }


    @DeleteMapping("/deaths/{id}")
    public Map<String, Boolean> deleteDeath(@PathVariable(value = "id") Long Id) throws Exception {
        Death death =
                repository
                        .findById(Id)
                        .orElseThrow(() -> new ResourceNotFoundException("Provider not found on :: " + Id));

        repository.delete(death);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
