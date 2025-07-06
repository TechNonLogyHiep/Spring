package org.example.baitap_apispringboot.service;

import org.example.baitap_apispringboot.dto.req.CertificateReq;
import org.example.baitap_apispringboot.dto.req.CertificateUpdateReq;
import org.example.baitap_apispringboot.dto.res.CertificateRes;

import java.util.List;

public interface CertificateService {
    List<CertificateRes> getAll();
    CertificateRes getById(int id);
    CertificateRes create(CertificateReq req);
    CertificateRes update(int id, CertificateUpdateReq req);
    void delete(int id);
}
