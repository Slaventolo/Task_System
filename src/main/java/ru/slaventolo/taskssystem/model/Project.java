package ru.slaventolo.taskssystem.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * Пустой конструктор для Spring
     */
    public Project() {}

    /**
     * Конструктор создания без передачи id
     */
    public Project(String name) {
        this.name = name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
