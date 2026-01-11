package com.keyin.sdat_devops_final_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "airlines")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 5)
    private String code; // AC, WS

    @Column(nullable = false)
    private String name; // Air Canada

    private String country;

    private String logoUrl; // optional

    public Airline() {}

    public Airline(String code, String name, String country, String logoUrl) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.logoUrl = logoUrl;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getCountry() { return country; }
    public String getLogoUrl() { return logoUrl; }

    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setCountry(String country) { this.country = country; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
}
