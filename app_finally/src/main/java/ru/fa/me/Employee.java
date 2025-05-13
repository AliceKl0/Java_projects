package ru.fa.me;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    // Обязательный конструктор без аргументов
    public Employee() {
    }

    // Конструктор только для name (id будет генерироваться автоматически)
    public Employee(String name) {
        this.name = name;
    }

    // Геттеры и сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

