package org.example.baitap_apispringboot.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.baitap_apispringboot.dto.req.LessonReq;
import org.example.baitap_apispringboot.dto.res.CategoryRes;
import org.example.baitap_apispringboot.dto.res.CertificateRes;
import org.example.baitap_apispringboot.dto.res.LessonRes;
import org.example.baitap_apispringboot.entity.Category;
import org.example.baitap_apispringboot.entity.Course;
import org.example.baitap_apispringboot.entity.Lessons;
import org.example.baitap_apispringboot.exception.AppException;
import org.example.baitap_apispringboot.exception.ErrorCode;
import org.example.baitap_apispringboot.repository.CourseRepository;
import org.example.baitap_apispringboot.repository.LessonsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LessonsServiceImpl implements LessonsService {
    LessonsRepository lessonsRepository;
    CourseRepository courseRepository;
    ModelMapper modelMapper;
    @Override
    public LessonRes getLessonById(int id) {
        Lessons findLesson = lessonsRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorCode.LESSONS_NOT_FOUND));
        return modelMapper.map(findLesson, LessonRes.class);
    }

    @Override
    public List<LessonRes> getAll() {
        List<Lessons> lessonRes = lessonsRepository.findAll();
        return lessonRes.stream().map(lessons -> modelMapper.map(lessons, LessonRes.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteLessonById(int id) {
        Lessons findLesson = lessonsRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorCode.LESSONS_NOT_FOUND));
        lessonsRepository.delete(findLesson);
    }

    @Override
    public LessonRes createLesson(LessonReq lessonReq) {
        Lessons lesson = new Lessons();
        lesson.setTitle(lessonReq.getTitle());
        lesson.setDescription(lessonReq.getDescription());
        lesson.setNumHours(lessonReq.getNumHours());
        if(lessonReq.getCourse_id() != null){
            Course course = courseRepository.findById(lessonReq.getCourse_id())
                    .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));
            lesson.setCourse(course);
        } else {
            lesson.setCourse(null);
        }
        return modelMapper.map(lessonsRepository.save(lesson), LessonRes.class);
    }

    @Override
    public LessonRes updateLesson(int id,LessonReq lessonReq) {
        Lessons findLesson = lessonsRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorCode.LESSONS_NOT_FOUND));
        Course course = courseRepository.findById(lessonReq.getCourse_id()).orElseThrow(()
                -> new AppException(ErrorCode.COURSE_NOT_FOUND));
        findLesson.setTitle(lessonReq.getTitle());
        findLesson.setDescription(lessonReq.getDescription());
        findLesson.setNumHours(lessonReq.getNumHours());
        findLesson.setCourse(course);
        return modelMapper.map(lessonsRepository.save(findLesson), LessonRes.class);
    }
}
