package com.tabibu.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeathDTO {
    private Long id;

    private int corpseAge;

    private long healthCareProviderId;

    private long diseaseId;

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

    public long getHealthCareProviderId() {
        return healthCareProviderId;
    }

    public void setHealthCareProviderId(long healthCareProviderId) {
        this.healthCareProviderId = healthCareProviderId;
    }

    public long getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(long diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }
}
