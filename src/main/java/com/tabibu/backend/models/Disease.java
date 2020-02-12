package com.tabibu.backend.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String description;

    @OneToMany(mappedBy = "disease")
    private List<Death> deaths;

    @ManyToMany(mappedBy = "diseases", fetch = FetchType.LAZY)
    private List<Diagnosis> diagnoses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Death> getDeaths() {
        return deaths;
    }

    public void setDeaths(List<Death> deaths) {
        this.deaths = deaths;
    }

    public DiseaseDTO convertToDTO() {
        DiseaseDTO diseaseDTO = new DiseaseDTO();
        diseaseDTO.setId(this.id);
        diseaseDTO.setName(this.name);
        diseaseDTO.setDescription(this.description);
        return diseaseDTO;
    }
}
