package com.enviro.assessment.grad001.austinngobeni.controller;

import com.enviro.assessment.grad001.austinngobeni.model.WasteCategories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ControllerInterface<T> {

    public ResponseEntity<T> updateById(@PathVariable Long id, @RequestBody T body);

    public ResponseEntity<T> getDataById(@PathVariable Long id);

    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id);

    public ResponseEntity<List<T>> getAllData();

    public ResponseEntity<T> addData(@RequestBody T requestBody);
}
