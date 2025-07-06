package org.example.baitap_apispringboot.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.baitap_apispringboot.dto.req.CourseReq;
import org.example.baitap_apispringboot.dto.res.CertificateRes;
import org.example.baitap_apispringboot.dto.res.CourseRes;
import org.example.baitap_apispringboot.entity.Category;
import org.example.baitap_apispringboot.entity.Course;
import org.example.baitap_apispringboot.repository.CategoryRepository;
import org.example.baitap_apispringboot.service.CategoryService;
import org.example.baitap_apispringboot.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CourseController {
    CourseService courseService;
    CategoryRepository categoryRepository;
    @GetMapping("/create")
    public String create(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "AddCourse";
    }

    @GetMapping("/getAll")
    public String getAllCourses(Model model) {
        List<CourseRes> courses = courseService.getAll();
        model.addAttribute("courses", courses);
        return "course";
    }

    @GetMapping("/edit/{id}")
    public String getCourseById(@PathVariable("id") int id, Model model) {
        CourseRes courseRes = courseService.getCourseById(id);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("courseRes", courseRes);
        return "editCourse";
    }

    @PostMapping("/add")
    public String createCourse(@ModelAttribute CourseReq course, Model model) {
        CourseRes courses = courseService.addCourse(course);
        model.addAttribute("courses", courses);
        return "redirect:/course/getAll";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@Valid @PathVariable("id") int id, @ModelAttribute CourseReq course) {
        courseService.updateCourse(id, course);
        return "redirect:/course/getAll";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
        return "redirect:/course/getAll";
    }
}
