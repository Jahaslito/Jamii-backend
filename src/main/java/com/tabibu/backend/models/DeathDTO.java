package com.tabibu.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeathDTO {
    private Long id;

    private int corpseAge;

    private HealthCareProviderDTO healthCareProvider;

    private DiseaseDTO disease;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String deathDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCorpseAge() {
        return corpseAge;
    }

    public void setCorpseAge(int corpseAge) {
        this.corpseAge = corpseAge;
    }

    public HealthCareProviderDTO getHealthCareProvider() {
        return healthCareProvider;
    }

    public void setHealthCareProvider(HealthCareProviderDTO healthCareProvider) {
        this.healthCareProvider = healthCareProvider;
    }

    public DiseaseDTO getDisease() {
        return disease;
    }

    public void setDisease(DiseaseDTO disease) {
        this.disease = disease;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }
}
