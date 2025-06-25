package org.example.baitap_apispringboot.entity;


import jakarta.persistence.*;
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
    private String name;

    @Column(name = "num_sessions")
    private Integer numSessions;

    @Column(name = "num_hours")
    private Integer numHours;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Certificate> certificateList = new ArrayList<>();

}
