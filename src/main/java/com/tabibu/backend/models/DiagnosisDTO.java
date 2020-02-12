package com.tabibu.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public class DiagnosisDTO {
    private Long id;

    private Long healthCareProviderId;

    private int patientAge;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String diagnosisDate;

    private List<Long> diseases;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHealthCareProviderId() {
        return healthCareProviderId;
    }

    public void setHealthCareProviderId(Long healthCareProviderId) {
        this.healthCareProviderId = healthCareProviderId;
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

    public List<Long> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Long> diseases) {
        this.diseases = diseases;
    }
}
