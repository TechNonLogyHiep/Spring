package org.example.baitap_apispringboot.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "course")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id",unique = true, nullable = false)
    private int id;

    @Column(name = "name",nullable = false, unique = true)
    @Size(min = 2, max = 200)
    private String name;

    @Column(name = "num_sessions")
    @Min(0)
    private Integer numSessions = 0;

    @Column(name = "num_hours")
    @Min(0)
    private Integer numHours = 0;


    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    List<Lessons> lessons = new ArrayList<>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Certificate> certificateList = new ArrayList<>();

}
