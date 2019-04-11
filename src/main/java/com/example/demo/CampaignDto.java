package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CampaignDto {

    private String title;
    private String description;
    private String brand;
    @DateTimeFormat(pattern = ("yyyy-mm-dd"))
    private LocalDate start;
    @DateTimeFormat(pattern = ("yyyy-mm-dd"))
    private LocalDate end;
    private Integer customerId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
