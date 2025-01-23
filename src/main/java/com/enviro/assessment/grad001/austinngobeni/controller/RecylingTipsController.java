package com.enviro.assessment.grad001.austinngobeni.controller;

import com.enviro.assessment.grad001.austinngobeni.model.RecyclingTips;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecylingTipsController extends JpaRepository<RecyclingTips, Long> {
}
