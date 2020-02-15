package com.tabibu.backend.models;

import java.util.List;

public class ReportDTO {
    private DiseaseDTO disease;
    private int year;
    private List<MonthCasesDTO> cases;

    public DiseaseDTO getDisease() {
        return disease;
    }

    public void setDisease(DiseaseDTO disease) {
        this.disease = disease;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<MonthCasesDTO> getCases() {
        return cases;
    }

    public void setCases(List<MonthCasesDTO> cases) {
        this.cases = cases;
    }
}
