package com.tabibu.backend.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "health_care_provider_id")
    private HealthCareProvider healthCareProvider;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "disease_diagnosis",
            joinColumns = @JoinColumn(name = "disease_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diagnosis_id",
                    referencedColumnName = "id"))
    private List<Disease> diseases;

    @Column(nullable = false)
    private int patientAge;

    @Temporal(TemporalType.DATE)
    private Date diagnosisDate;
}
