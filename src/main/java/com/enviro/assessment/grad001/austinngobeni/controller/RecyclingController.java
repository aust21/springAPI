package com.enviro.assessment.grad001.austinngobeni.controller;

import com.enviro.assessment.grad001.austinngobeni.model.RecyclingTips;
import com.enviro.assessment.grad001.austinngobeni.repo.RecylingTipsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rec")
public class RecyclingController implements ControllerInterface<RecyclingTips>{

    @Autowired
    private RecylingTipsRepo recyclingRepo;

    @Override
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
    public ResponseEntity<RecyclingTips> getDataById(Long id) {
        Optional<RecyclingTips> data = recyclingRepo.findById(id);

        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<RecyclingTips>> getAllData() {
        return null;
    }

    @Override
    public ResponseEntity<RecyclingTips> addData(RecyclingTips requestBody) {
        return null;
    }
}
