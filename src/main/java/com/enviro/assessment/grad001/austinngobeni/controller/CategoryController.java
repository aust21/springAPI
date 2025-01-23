package com.enviro.assessment.grad001.austinngobeni.controller;

import com.enviro.assessment.grad001.austinngobeni.model.WasteCategories;
import com.enviro.assessment.grad001.austinngobeni.repo.WasteCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cat")
public class CategoryController implements ControllerInterface<WasteCategories>{

    @Autowired
    private WasteCategoryRepo categoryRepo;

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<WasteCategories> updateById(@PathVariable Long id, @RequestBody WasteCategories newData) {
        Optional<WasteCategories> existingData = categoryRepo.findById(id);

        if (existingData.isPresent()) {
            WasteCategories updatedData = existingData.get();

            updatedData.setDisposal(newData.getDisposal());
            WasteCategories wasteObj = categoryRepo.save(updatedData);
            return new ResponseEntity<>(wasteObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @GetMapping("/get-data/{id}")
    public ResponseEntity<WasteCategories> getDataById(@PathVariable Long id) {
        Optional<WasteCategories> data = categoryRepo.findById(id);

        if (data.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(data.get(), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/delete-data/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        categoryRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @GetMapping("/getAllData")
    public ResponseEntity<List<WasteCategories>> getAllData() {
        List<WasteCategories> data = new ArrayList<>();
        categoryRepo.findAll().forEach(data::add);

        if (data.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<WasteCategories> addData(@RequestBody WasteCategories category) {
        WasteCategories savedCategory = categoryRepo.save(category);
        return ResponseEntity.ok(savedCategory);
    }
}
