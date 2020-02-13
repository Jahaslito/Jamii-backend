package com.tabibu.backend.models;

public class DiseaseDTO {
    private Long id;
    private String name;
    private String description;

    public DiseaseDTO() {}

    public DiseaseDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

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

    public Disease convertToEntity() {
        Disease disease = new Disease();
        disease.setName(this.name);
        disease.setDescription(this.description);
        return disease;
    }
}
