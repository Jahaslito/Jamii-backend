package com.tabibu.backend.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String description;

    @ManyToMany(mappedBy = "diseases")
    private List<Diagnosis> diagnoses;

    @OneToMany(mappedBy = "disease")
    private List<Death> deaths;

}
