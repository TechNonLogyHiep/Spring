package org.example.baitap_apispringboot.service;

import org.example.baitap_apispringboot.dto.req.CateogryReq;
import org.example.baitap_apispringboot.dto.req.CourseReq;
import org.example.baitap_apispringboot.dto.res.CategoryRes;
import org.example.baitap_apispringboot.dto.res.CourseRes;
import org.example.baitap_apispringboot.entity.Course;

import java.util.List;

public interface CourseService {
    CourseRes getCourseById(int id);
    List<CourseRes> getAll();
    CourseRes addCourse(CourseReq courseReq);
    CourseRes updateCourse(int id,CourseReq courseReq);
    void deleteCourse(int id);
}
