package org.example.baitap_apispringboot.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.baitap_apispringboot.dto.req.CertificateReq;
import org.example.baitap_apispringboot.dto.req.CertificateUpdateReq;
import org.example.baitap_apispringboot.dto.res.CertificateRes;
import org.example.baitap_apispringboot.entity.Certificate;
import org.example.baitap_apispringboot.service.CertificateServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificate")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CertificateController {
    CertificateServiceImpl certificateService;

    @PostMapping("/createCertificateAchieved/{courseId}")
    public ResponseEntity<?> createCertificateAchieved(@PathVariable int courseId,@RequestBody CertificateReq certificateReq) {
        return ResponseEntity.ok(certificateService.createCertificateAchieved(courseId,certificateReq));
    }
    @GetMapping("/getByCourseId/{courseId}")
    public ResponseEntity<?> getCertificate(@PathVariable int courseId) {
        return ResponseEntity.ok(certificateService.getByCourseId(courseId));
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(certificateService.getAll());
    }

}
