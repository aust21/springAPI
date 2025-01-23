package com.enviro.assessment.grad001.austinngobeni.controller;

import com.enviro.assessment.grad001.austinngobeni.model.WasteCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteCategoryController extends JpaRepository<WasteCategories, Long> {
}
