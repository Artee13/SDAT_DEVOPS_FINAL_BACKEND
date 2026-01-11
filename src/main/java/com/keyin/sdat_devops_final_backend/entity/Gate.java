package com.keyin.sdat_devops_final_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "gates")
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // A1, B2, etc.

    private String terminal; // optional

    @ManyToOne(optional = false)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    public Gate() {}

    public Gate(String name, String terminal, Airport airport) {
        this.name = name;
        this.terminal = terminal;
        this.airport = airport;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getTerminal() { return terminal; }
    public Airport getAirport() { return airport; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setTerminal(String terminal) { this.terminal = terminal; }
    public void setAirport(Airport airport) { this.airport = airport; }
}
