package ru.evdokimov.restTemplate.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class User implements Serializable {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Byte age) {
        this.age = age;
    }
}
