package org.example.baitap_apispringboot.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.baitap_apispringboot.dto.req.CourseReq;
import org.example.baitap_apispringboot.entity.Course;
import org.example.baitap_apispringboot.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CourseController {
    CourseService courseService;
    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        try {
            return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseReq course) {
            return new ResponseEntity<>(courseService.addCourse(course), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@Valid @PathVariable("id") int id, @RequestBody CourseReq course) {
            return new ResponseEntity<>(courseService.updateCourse(id, course), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") int id) {
        try {
            courseService.deleteCourse(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
