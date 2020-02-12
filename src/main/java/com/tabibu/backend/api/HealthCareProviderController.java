package com.tabibu.backend.api;

import com.tabibu.backend.exceptions.ResourceNotFoundException;
import com.tabibu.backend.models.HealthCareProvider;
import com.tabibu.backend.models.HealthCareProviderDTO;
import com.tabibu.backend.repositories.HealthCareProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class HealthCareProviderController {

    private final HealthCareProviderRepository repository;

    @Autowired
    public HealthCareProviderController(HealthCareProviderRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/providers")
    public List<HealthCareProviderDTO> getAllProviders() {
        return repository.findAll().stream().map(HealthCareProvider::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/providers/{id}")
    public ResponseEntity<HealthCareProviderDTO> getProvidersById(@PathVariable(value = "id") Long providerId)
            throws ResourceNotFoundException {
        HealthCareProvider provider =
                repository
                        .findById(providerId)
                        .orElseThrow(() -> new ResourceNotFoundException("Provider not found on id: " + providerId));
        return ResponseEntity.ok().body(provider.convertToDTO());
    }

    @PostMapping("/providers")
    public HealthCareProviderDTO createProvider(@Valid @RequestBody HealthCareProviderDTO providerDTO) {
        HealthCareProvider provider = providerDTO.convertToEntity();
        repository.save(provider);
        return provider.convertToDTO();
    }

    @DeleteMapping("/providers/{id}")
    public Map<String, Boolean> deleteProvider(@PathVariable(value = "id") Long providerId) throws Exception {
        HealthCareProvider provider =
                repository
                        .findById(providerId)
                        .orElseThrow(() -> new ResourceNotFoundException("Provider not found on id: " + providerId));

        repository.delete(provider);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
