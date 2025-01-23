package com.enviro.assessment.grad001.austinngobeni.controller;

import com.enviro.assessment.grad001.austinngobeni.model.Guidelines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guide")
public class GuidelineController implements ControllerInterface<Guidelines>{

    @Autowired
    private GuidelineController guideController;

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<Guidelines> updateById(Long id, Guidelines body) {
        return null;
    }

    @Override
    @GetMapping("/get/{id}")
    public ResponseEntity<Guidelines> getDataById(Long id) {
        return null;
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        return null;
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
