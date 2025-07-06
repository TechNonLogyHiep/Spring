package org.example.baitap_apispringboot.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.baitap_apispringboot.dto.req.CertificateReq;
import org.example.baitap_apispringboot.dto.req.CertificateUpdateReq;
import org.example.baitap_apispringboot.dto.res.CertificateRes;
import org.example.baitap_apispringboot.entity.Certificate;
import org.example.baitap_apispringboot.entity.Course;
import org.example.baitap_apispringboot.repository.CertificateRepository;
import org.example.baitap_apispringboot.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    CertificateRepository certificateRepository;
    CourseRepository courseRepository;
    ModelMapper modelMapper;
    @Override
    public List<CertificateRes> getAll() {
        List<Certificate> certificates = certificateRepository.findAll();
        return certificates.stream().map(certificate -> modelMapper.map(certificate, CertificateRes.class)).collect(Collectors.toList());
    }
    @Override
    public CertificateRes getById(int id) {
       Certificate certificate = certificateRepository.findById(id).orElse(null);
       return modelMapper.map(certificate, CertificateRes.class);
    }

    @Override
    public CertificateRes create(CertificateReq req) {
        Course course = courseRepository.findById(req.getCourse_id())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + req.getCourse_id()));
        Certificate certificate = modelMapper.map(req, Certificate.class);
        certificate.setCourse(course);
        Certificate saved = certificateRepository.save(certificate);
        return modelMapper.map(saved, CertificateRes.class);
    }

    @Override
    public CertificateRes update(int id, CertificateUpdateReq req) {
        Certificate existCertificate = certificateRepository.findById(id).orElse(null);
        if(existCertificate == null) {
            throw new RuntimeException("Certificate not found");
        }

        Course course = courseRepository.findById(req.getCourse_id())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        existCertificate.setType(req.getType());
        existCertificate.setStudentName(req.getStudentName());
        existCertificate.setIssueDate(req.getIssueDate());
        existCertificate.setAchieved(req.isAchieved());
        existCertificate.setCourse(course);
        Certificate updated = certificateRepository.save(existCertificate);
        return modelMapper.map(updated, CertificateRes.class);
    }


    @Override
    public void delete(int id) {
        Certificate certificate = certificateRepository.findById(id).orElse(null);
        if(certificate == null) {
            throw new RuntimeException("Certificate not found");
        }
        certificateRepository.delete(certificate);
    }
}
