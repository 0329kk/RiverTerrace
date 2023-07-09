package com.example.RiverTerrace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RiverTerrace.entity.Plan;
import com.example.RiverTerrace.entity.Reservation;
import com.example.RiverTerrace.repository.PlanRepository;
import com.example.RiverTerrace.repository.ReservationRepository;

@Service
public class ReservationService {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PlanRepository planRepository;
	
	public List<Plan> getAll() {
		return planRepository.findAll();
	}

	public void saverReservationUser(Reservation reservation) {

		reservationRepository.save(reservation);
	}

	public Reservation findByReservationid(String reservationid) {
		return reservationRepository.findByReservationidLike(reservationid);
	}

	public Reservation findByReservationUserId(Integer idInteger) {
		// TODO 自動生成されたメソッド・スタブ
		return reservationRepository.findById(idInteger).orElse(null);
	}

	public void reservationDeleteUser(Integer id) {
		reservationRepository.deleteById(id);
		
	}




}
