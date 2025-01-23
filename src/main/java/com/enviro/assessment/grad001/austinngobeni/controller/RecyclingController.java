package com.enviro.assessment.grad001.austinngobeni.controller;

import com.enviro.assessment.grad001.austinngobeni.model.RecyclingTips;
import com.enviro.assessment.grad001.austinngobeni.repo.RecylingTipsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rec")
public class RecyclingController implements ControllerInterface<RecyclingTips>{

    @Autowired
    private RecylingTipsRepo recyclingRepo;

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<RecyclingTips> updateById(Long id, RecyclingTips body) {
        Optional<RecyclingTips> existingData = recyclingRepo.findById(id);

        if (existingData.isPresent()) {
            RecyclingTips updatedData = existingData.get();
            updatedData.setRecyclingTip(body.getRecyclingTip());
            RecyclingTips newObj = recyclingRepo.save(updatedData);
            return new ResponseEntity<>(newObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @GetMapping("/get-data/{id}")
    public ResponseEntity<RecyclingTips> getDataById(Long id) {
        Optional<RecyclingTips> data = recyclingRepo.findById(id);

        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @DeleteMapping("/delete-data/{id}")
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        Optional<RecyclingTips> data = recyclingRepo.findById(id);

        if (data.isPresent()) {
            recyclingRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @GetMapping("/getAllData")
    public ResponseEntity<List<RecyclingTips>> getAllData() {
        List<RecyclingTips> data = new ArrayList<>();
        recyclingRepo.findAll().forEach(data::add);

        if (data.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<RecyclingTips> addData(RecyclingTips requestBody) {
        RecyclingTips newData = recyclingRepo.save(requestBody);
        return ResponseEntity.ok(newData);
    }
}
