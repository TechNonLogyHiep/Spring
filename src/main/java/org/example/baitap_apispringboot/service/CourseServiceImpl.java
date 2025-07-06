package org.example.baitap_apispringboot.service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.baitap_apispringboot.dto.req.CourseReq;
import org.example.baitap_apispringboot.dto.res.CourseRes;
import org.example.baitap_apispringboot.entity.Category;
import org.example.baitap_apispringboot.entity.Course;
import org.example.baitap_apispringboot.exception.AppException;
import org.example.baitap_apispringboot.exception.ErrorCode;
import org.example.baitap_apispringboot.repository.CategoryRepository;
import org.example.baitap_apispringboot.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Service
public class CourseServiceImpl implements CourseService {
    CourseRepository courseRepository;
    CategoryRepository categoryRepository;
    ModelMapper modelMapper;
    @Override
    public CourseRes getCourseById(int id) {
        Course course = courseRepository.findById(id).orElseThrow(()
                -> new AppException(ErrorCode.COURSE_NOT_FOUND));
        return modelMapper.map(course, CourseRes.class);
    }

    @Override
    public List<CourseRes> getAll() {
       List<Course> courses = courseRepository.findAll();
       return courses.stream().map(course -> modelMapper.map(course, CourseRes.class)).collect(Collectors.toList());
    }

    @Override
    public CourseRes addCourse(CourseReq courseReq) {
        Category category = categoryRepository.findById(courseReq.getCategory_id()).orElseThrow(()
                -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        Course course = new Course();
        course.setName(courseReq.getName());
        course.setNumSessions(courseReq.getNumSessions());
        course.setNumHours(courseReq.getNumHours());
        course.setDescription(courseReq.getDescription());
        course.setCategory(category);
        Course savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseRes.class);
    }

    @Override
    public CourseRes updateCourse(int id, CourseReq courseReq) {
        Course findToUpdate = courseRepository.findById(id).orElseThrow(()
                -> new AppException(ErrorCode.COURSE_NOT_FOUND));
        Category category = categoryRepository.findById(courseReq.getCategory_id()).orElseThrow(()
                -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        findToUpdate.setName(courseReq.getName());
        findToUpdate.setNumSessions(courseReq.getNumSessions());
        findToUpdate.setNumHours(courseReq.getNumHours());
        findToUpdate.setDescription(courseReq.getDescription());
        findToUpdate.setCategory(category);
        return modelMapper.map(courseRepository.save(findToUpdate), CourseRes.class);
    }

    @Override
    public void deleteCourse(int id) {
        Course findToUpdate = courseRepository.findById(id).orElseThrow(()
                ->new  AppException(ErrorCode.COURSE_NOT_FOUND));
        courseRepository.delete(findToUpdate);
    }
}
