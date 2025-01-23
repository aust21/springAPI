package com.enviro.assessment.grad001.austinngobeni.repo;

import com.enviro.assessment.grad001.austinngobeni.model.WasteCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteCategoryRepo extends JpaRepository<WasteCategories, Long> {
}
