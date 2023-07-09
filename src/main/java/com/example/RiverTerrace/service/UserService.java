package com.example.RiverTerrace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RiverTerrace.entity.User;
import com.example.RiverTerrace.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public void inquiryUser(User user) {
		userRepository.save(user);
	}

}
