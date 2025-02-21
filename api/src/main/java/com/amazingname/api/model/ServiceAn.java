package com.amazingname.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "service")  // Use ServiceAn in code to not conflict with org.springframework.stereotype.Service
public class ServiceAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

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
}
