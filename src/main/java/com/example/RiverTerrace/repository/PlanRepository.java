package com.example.RiverTerrace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RiverTerrace.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

	Plan findByPlannameLike(String planname);

}
