package ru.slaventolo.taskssystem.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Project") // Я переименовал класс, но в аннотации можно явно указать название таблицы
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * Пустой конструктор для Spring
     */
    public ProjectEntity() {}

    /**
     * конструктор создания без передачи id
     */
    public ProjectEntity(String name) {
        this.name = name;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
