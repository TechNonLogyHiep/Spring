package org.example.baitap_apispringboot.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.baitap_apispringboot.dto.req.CateogryReq;
import org.example.baitap_apispringboot.dto.res.CategoryRes;
import org.example.baitap_apispringboot.entity.Category;
import org.example.baitap_apispringboot.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    ModelMapper modelMapper;
    @Override
    public List<CategoryRes> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category ->
                modelMapper.map(category, CategoryRes.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryRes findById(int id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return modelMapper.map(category, CategoryRes.class);
    }

    @Override
    public CategoryRes save(CateogryReq category) {
       Category categoryEntity = modelMapper.map(category, Category.class);
       Category savedCategory = categoryRepository.save(categoryEntity);
       return modelMapper.map(savedCategory, CategoryRes.class);
    }

    @Override
    public CategoryRes update(int id, CateogryReq categoryReq) {
       Category catExist = categoryRepository.findById(id).orElse(null);
       if(catExist == null) {
           throw new RuntimeException("không tìm thấy category_id");
       }
       catExist.setName(categoryReq.getName());
       Category updatedCategory = categoryRepository.save(catExist);
       return modelMapper.map(updatedCategory, CategoryRes.class);
    }

    @Override
    public void delete(int id) {
        Category category = categoryRepository.findById(id).orElse(null);
        categoryRepository.delete(category);
    }
}
