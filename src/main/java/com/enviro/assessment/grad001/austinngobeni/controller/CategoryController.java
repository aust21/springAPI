package com.enviro.assessment.grad001.austinngobeni.controller;

import com.enviro.assessment.grad001.austinngobeni.model.WasteCategories;
import com.enviro.assessment.grad001.austinngobeni.repo.WasteCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public class CategoryController implements ControllerInterface<WasteCategories>{

    @Autowired
    private WasteCategoryRepo categoryRepo;

    @Override
    @PutMapping("/cat?id={id}")
    public ResponseEntity<WasteCategories> updateById(@PathVariable Long id) {
        Optional<WasteCategories> data = categoryRepo.findById(id);

        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        WasteCategories savedCategory = categoryRepo.save(category);
        return ResponseEntity.ok(savedCategory);
    }
}
