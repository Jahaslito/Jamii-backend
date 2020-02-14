package com.tabibu.backend.models;

public class MonthCasesDTO {
    private String month;
    private int totalCases;

    public MonthCasesDTO(String month, int totalCases) {
        this.month = month;
        this.totalCases = totalCases;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }
}
