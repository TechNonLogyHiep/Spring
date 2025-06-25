package org.example.baitap_apispringboot.controller;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.Response;
import org.example.baitap_apispringboot.dto.req.CateogryReq;
import org.example.baitap_apispringboot.dto.res.CategoryRes;
import org.example.baitap_apispringboot.service.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryController {
    CategoryServiceImpl categoryService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.ok(categoryService.findById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<?> add(@RequestBody CateogryReq res){
        CategoryRes c = categoryService.save(res);
        return ResponseEntity.ok(c);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody CateogryReq res){
        return ResponseEntity.ok(categoryService.update(id, res));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            categoryService.delete(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
