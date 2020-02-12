package com.tabibu.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "health_care_provider_id")
    private HealthCareProvider healthCareProvider;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "disease_diagnosis",
            joinColumns = {
                    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "disease_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private List<Disease> diseases;

    @Column(nullable = false)
    private int patientAge;

    @Temporal(TemporalType.DATE)
    private Date diagnosisDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HealthCareProvider getHealthCareProvider() {
        return healthCareProvider;
    }

    public void setHealthCareProvider(HealthCareProvider healthCareProvider) {
        this.healthCareProvider = healthCareProvider;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public DiagnosisDTO convertToDTO() {
        DiagnosisDTO diagnosisDTO = new DiagnosisDTO();
        diagnosisDTO.setId(this.id);
        diagnosisDTO.setHealthCareProviderId(this.healthCareProvider.getId());
        diagnosisDTO.setPatientAge(this.patientAge);
        diagnosisDTO.setDiagnosisDate(this.diagnosisDate.toString());
        diagnosisDTO.setDiseases(this.getDiseases().stream().map(Disease::getId).collect(Collectors.toList()));
        return diagnosisDTO;
    }
}
