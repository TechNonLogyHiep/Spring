package org.example.baitap_apispringboot.service;

import org.example.baitap_apispringboot.dto.req.CateogryReq;
import org.example.baitap_apispringboot.dto.res.CategoryRes;
import org.example.baitap_apispringboot.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryRes> findAll();
    CategoryRes findById(int id);
    CategoryRes save(CateogryReq category);
    CategoryRes update(int id,CateogryReq category);
    void delete(int id);
}
