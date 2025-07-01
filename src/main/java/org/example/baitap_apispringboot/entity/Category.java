package org.example.baitap_apispringboot.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@Table(name = "category")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id",unique = true,nullable=false)
    private int category_id;

    @Column(name = "name",nullable=false,columnDefinition = "VARCHAR(50)")

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Course> courses;
}
