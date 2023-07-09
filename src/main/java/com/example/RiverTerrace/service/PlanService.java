package com.example.RiverTerrace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RiverTerrace.entity.Plan;
import com.example.RiverTerrace.repository.PlanRepository;

@Service
public class PlanService {
	
	@Autowired
	PlanRepository planRepository;

	public Plan findPlan(Integer id) {
		return planRepository.findById(id).orElse(null);
	}

	public Plan findByPlanNameLike(String planname) {
		// TODO 自動生成されたメソッド・スタブ
		return planRepository.findByPlannameLike(planname);
	}

}
