package com.tabibu.backend.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Death {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Column(nullable = false)
    private int corpseAge;

    @ManyToOne
    @JoinColumn(name = "health_care_provider_id")
    private HealthCareProvider healthCareProvider;

    @ManyToOne
    @JoinColumn(name= "disease_id")
    private Disease disease;

    @Temporal(TemporalType.DATE)
    private Date deathDate;
}
