package com.enviro.assessment.grad001.austinngobeni.controller;

import com.enviro.assessment.grad001.austinngobeni.model.WasteCategories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ControllerInterface<T> {

    public ResponseEntity<T> updateById(@PathVariable Long id);

    public ResponseEntity<T> getData();

    public ResponseEntity<T> deleteById();

    public ResponseEntity<T> getAllData();

    public ResponseEntity<T> addData(@RequestBody T requestBody);
}
