package org.example.baitap_apispringboot.controller;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.Response;
import org.example.baitap_apispringboot.dto.req.CateogryReq;
import org.example.baitap_apispringboot.dto.res.CategoryRes;
import org.example.baitap_apispringboot.service.CategoryService;
import org.example.baitap_apispringboot.service.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryController {
    CategoryService categoryService;

    @GetMapping("/getAll")
    public String getAll(Model model) {
        List<CategoryRes> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "categoryIndex";
    }

    @GetMapping("/edit/{id}")
    public String getById(@PathVariable int id,Model model) {
       CategoryRes categoryRes =  categoryService.findById(id);
        model.addAttribute("category", categoryRes);
       return "updateCategory";
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "addFormCategory";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute CateogryReq res) {
        CategoryRes c = categoryService.save(res);
        return "redirect:/category/getAll";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id, @ModelAttribute CateogryReq res) {
        categoryService.update(id, res);
        return "redirect:/category/getAll";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        categoryService.delete(id);
        return "redirect:/category/getAll";
    }
}
