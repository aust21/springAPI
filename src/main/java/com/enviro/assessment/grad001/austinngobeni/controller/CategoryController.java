package com.enviro.assessment.grad001.austinngobeni.controller;

import com.enviro.assessment.grad001.austinngobeni.model.WasteCategories;
import com.enviro.assessment.grad001.austinngobeni.repo.WasteCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CategoryController implements ControllerInterface<WasteCategories>{

    @Autowired
    private WasteCategoryRepo wasteCategories;

    @Override
    @PutMapping("/cat?id={id}")
    public ResponseEntity<WasteCategories> updateById(@PathVariable Long id) {
        return null;
    }

    @Override
    public ResponseEntity<WasteCategories> getData() {
        return null;
    }

    @Override
    public ResponseEntity<WasteCategories> deleteById() {
        return null;
    }

    @Override
    public ResponseEntity<WasteCategories> getAllData() {
        return null;
    }

    @Override
    @PostMapping("/cat/add")
    public ResponseEntity<WasteCategories> addData(@RequestBody WasteCategories category) {
        WasteCategories savedCategory = wasteCategories.save(category);
        return ResponseEntity.ok(savedCategory);
    }
}
