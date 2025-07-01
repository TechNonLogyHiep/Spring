package org.example.baitap_apispringboot.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.baitap_apispringboot.dto.req.LessonReq;
import org.example.baitap_apispringboot.service.LessonsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LessonsController {
    LessonsService lessonsService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAll(@PathVariable int id) {
        try {
            return ResponseEntity.ok(lessonsService.getLessonById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody LessonReq lessonReq){
            return new ResponseEntity<>(lessonsService.createLesson(lessonReq), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable int id, @RequestBody LessonReq lessonReq){
            return new ResponseEntity<>(lessonsService.updateLesson(id, lessonReq), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            lessonsService.deleteLessonById(id);
            return new ResponseEntity<>("Deleted Sucessfully!",HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
