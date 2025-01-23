package com.enviro.assessment.grad001.austinngobeni.controller;

import com.enviro.assessment.grad001.austinngobeni.model.Guidelines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideLineController extends JpaRepository<Guidelines, Long> {
}
