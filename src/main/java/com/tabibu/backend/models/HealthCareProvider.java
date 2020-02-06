package com.tabibu.backend.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class HealthCareProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int phone;

    @OneToMany(mappedBy = "healthCareProvider")
    private List<Diagnosis> diagnoses;

    @OneToMany(mappedBy = "healthCareProvider")
    private List<Death> deaths;

}
