package org.example.baitap_apispringboot.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.baitap_apispringboot.dto.req.CateogryReq;
import org.example.baitap_apispringboot.dto.req.CertificateReq;
import org.example.baitap_apispringboot.dto.req.CertificateUpdateReq;
import org.example.baitap_apispringboot.dto.res.CertificateRes;
import org.example.baitap_apispringboot.dto.res.CourseRes;
import org.example.baitap_apispringboot.entity.Certificate;
import org.example.baitap_apispringboot.entity.Course;
import org.example.baitap_apispringboot.repository.CourseRepository;
import org.example.baitap_apispringboot.service.CertificateService;
import org.example.baitap_apispringboot.service.CertificateServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/certificate")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CertificateController {
    CertificateService certificateService;
    CourseRepository courseRepository;
    @GetMapping("/create")
    public String create(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "AddCertificate";
    }

    @PostMapping("/add")
    public String createCertificateAchieved(@ModelAttribute CertificateReq certificateReq, Model model) {
        CertificateRes res = certificateService.create(certificateReq);
        model.addAttribute("certificate", res);
        return "redirect:/certificate/getAll";
    }

    @GetMapping("/edit/{id}")
    public String getCertificate(@PathVariable int id,Model model) {
       CertificateRes certificateRes = certificateService.getById(id);
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
       model.addAttribute("certificateRes", certificateRes);
       return "editCertificate";
    }
    @GetMapping("/getAll")
    public String getAll(Model model) {
        List<CertificateRes> certificateResList = certificateService.getAll();
        model.addAttribute("certificateResList",certificateResList);
        return "certificate";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable int id, @ModelAttribute CertificateUpdateReq res) {
        certificateService.update(id, res);
        return "redirect:/certificate/getAll";
    }

    @GetMapping("/delete/{id}")
    public String deleteCertificate(@PathVariable int id) {
        certificateService.delete(id);
        return "redirect:/certificate/getAll";
    }

}
