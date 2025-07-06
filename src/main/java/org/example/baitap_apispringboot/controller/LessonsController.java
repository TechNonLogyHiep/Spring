package org.example.baitap_apispringboot.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.baitap_apispringboot.dto.req.LessonReq;
import org.example.baitap_apispringboot.dto.res.LessonRes;
import org.example.baitap_apispringboot.entity.Course;
import org.example.baitap_apispringboot.repository.CourseRepository;
import org.example.baitap_apispringboot.service.LessonsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lessons")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LessonsController {
    LessonsService lessonsService;
    CourseRepository courseRepository;

    @GetMapping("/edit/{id}")
    public String getById(@PathVariable int id, Model model) {
        LessonRes lessons = lessonsService.getLessonById(id);
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("lessons", lessons);
        return "editLesson";

    }
    @GetMapping("/create")
    public String create(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "addLesson";
    }
    @PostMapping("/add-to-course")
    public String create(@Valid @ModelAttribute LessonReq lessonReq,Model model) {
        LessonRes lesson = lessonsService.createLesson(lessonReq);
        model.addAttribute("lesson",lesson);
        return "redirect:/lessons/getAll";

    }

    @PostMapping("/update/{id}")
    public String update(@Valid @PathVariable int id, @ModelAttribute LessonReq lessonReq) {
        lessonsService.updateLesson(id, lessonReq);
        return "redirect:/lessons/getAll";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        lessonsService.deleteLessonById(id);
        return "redirect:/lessons/getAll";
    }

    @GetMapping("/getAll")
    public String getAll(Model model) {
        List<LessonRes> lessons = lessonsService.getAll();
        model.addAttribute("lessons", lessons);
        return "lessons";
    }
}
