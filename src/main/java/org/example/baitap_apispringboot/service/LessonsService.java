package org.example.baitap_apispringboot.service;

import org.example.baitap_apispringboot.dto.req.LessonReq;
import org.example.baitap_apispringboot.dto.res.LessonRes;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LessonsService {
    List<LessonRes> getAll();
    LessonRes getLessonById(int id);
    void deleteLessonById(int id);
    LessonRes createLesson(LessonReq lesson);
    LessonRes updateLesson(int id,LessonReq lesson);
}
