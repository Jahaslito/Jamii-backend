package com.tabibu.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public class DiagnosisDTO {
    private Long id;

    private HealthCareProviderDTO healthCareProvider;

    private int patientAge;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String diagnosisDate;

    private List<DiseaseDTO> diseases;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HealthCareProviderDTO getHealthCareProvider() {
        return healthCareProvider;
    }

    public void setHealthCareProvider(HealthCareProviderDTO healthCareProvider) {
        this.healthCareProvider = healthCareProvider;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(String diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public List<DiseaseDTO> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<DiseaseDTO> diseases) {
        this.diseases = diseases;
    }
}
