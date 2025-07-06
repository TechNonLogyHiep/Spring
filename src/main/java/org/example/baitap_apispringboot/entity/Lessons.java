package org.example.baitap_apispringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.baitap_apispringboot.exception.ErrorCode;

import java.util.List;

@Data
@Entity
@Table(name ="lessons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lessons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "lession_id",nullable = false)
    int lesson_id;

    @Column(name = "title", nullable = false, unique = true)
            @Size(max = 200,min = 3)
    String title;
    @Column(name ="description")
    String description;

    @Column(name = "num_hours")
    @Min(0)
    private Integer numHours = 0;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = true)
    private Course course;

}
