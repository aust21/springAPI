package com.enviro.assessment.grad001.austinngobeni.controller;

import com.enviro.assessment.grad001.austinngobeni.model.Guidelines;
import com.enviro.assessment.grad001.austinngobeni.repo.GuideLineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guide")
public class GuidelineController implements ControllerInterface<Guidelines>{

    @Autowired
    private GuideLineRepo guideRepo;

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<Guidelines> updateById(Long id, Guidelines body) {
        Optional<Guidelines> existingData = guideRepo.findById(id);

        if (existingData.isPresent()) {
            Guidelines updatedData = existingData.get();
            updatedData.setGuideline(body.getGuideline());
            Guidelines newObj = guideRepo.save(updatedData);
            return new ResponseEntity<>(newObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @GetMapping("/get/{id}")
    public ResponseEntity<Guidelines> getDataById(Long id) {
        Optional<Guidelines> data = guideRepo.findById(id);

        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        Optional<Guidelines> data = guideRepo.findById(id);

        if (data.isPresent()) {
            guideRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @GetMapping("/get")
    public ResponseEntity<List<Guidelines>> getAllData() {
        return null;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Guidelines> addData(Guidelines requestBody) {
        return null;
    }
}
