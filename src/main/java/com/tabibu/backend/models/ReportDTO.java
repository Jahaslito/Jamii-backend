package com.tabibu.backend.models;

import java.util.List;

public class ReportDTO {
    private String disease;
    private int year;
    private List<MonthCasesDTO> cases;

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
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
