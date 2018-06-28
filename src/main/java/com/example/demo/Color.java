package com.example.demo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String colorName;

    @ManyToMany(mappedBy = "colors")
    private Set<Person> owners;

    public Color() {
        owners = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Set<Person> getOwners() {
        return owners;
    }

    public void setOwners(Set<Person> owners) {
        this.owners = owners;
    }

}