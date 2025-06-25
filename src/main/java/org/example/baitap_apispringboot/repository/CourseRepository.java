package org.example.baitap_apispringboot.repository;

import org.example.baitap_apispringboot.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
