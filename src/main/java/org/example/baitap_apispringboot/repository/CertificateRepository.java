package org.example.baitap_apispringboot.repository;

import org.example.baitap_apispringboot.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
    List<Certificate> findByCourseId(int courseId);
    Optional<Certificate> findByCourse_IdAndStudentName(int id, String studentName);

}
