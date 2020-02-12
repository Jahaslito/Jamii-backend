package com.tabibu.backend.models;

public class HealthCareProviderDTO {
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String location;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HealthCareProvider convertToEntity() {
        HealthCareProvider provider = new HealthCareProvider();
        provider.setName(this.name);
        provider.setPhone(this.phone);
        provider.setEmail(this.email);
        provider.setLocation(this.location);
        return provider;
    }
}
