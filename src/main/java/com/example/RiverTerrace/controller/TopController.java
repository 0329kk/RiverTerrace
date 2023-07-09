package com.example.RiverTerrace.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.RiverTerrace.entity.User;
import com.example.RiverTerrace.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TopController {
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/inquiryUser")
	public ModelAndView handleGetRequest() {
		ModelAndView mav = new ModelAndView();
		User user = new User();
		List<String> errorMessages = (List<String>) session.getAttribute("errorMessages");
		List<String> blank = new ArrayList<>();
		session.setAttribute("errorMessages", blank);
		mav.addObject("errorMessages", errorMessages);
		mav.addObject("formModel", user);
		mav.setViewName("/top");
		return mav;
	}
	
	@GetMapping("/top")
	public ModelAndView top() {
		
		ModelAndView mav = new ModelAndView();
		User user = new User();
//		List<String> errorMessages = (List<String>) session.getAttribute("errorMessages");
//		List<String> blank = new ArrayList<>();
//		session.setAttribute("errorMessages", blank);
//		mav.addObject("errorMessages", errorMessages);
		mav.addObject("formModel", user);
		mav.setViewName("/top");
		
		return mav;
		
	}
	
	@GetMapping("/confirm")
	public ModelAndView confirm() {
		
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/confirm");
		
		return mav;
		
	}
	
	@PostMapping("/inquiryUser")
	public ModelAndView inquiryUser(@ModelAttribute(name = "formModel") User user) throws ParseException {
		
			ModelAndView mav = new ModelAndView();
			List<String> errorMessages = new ArrayList<>();
			
			if (user.getName().isEmpty() || user.getFurigana().isEmpty() || user.getPhone_number().isEmpty() || user.getEmail().isEmpty() || user.getDescription().isEmpty()) {
				mav.addObject("formModel", user);
				errorMessages.add("必須項目は必ず入力してください");
				session.setAttribute("errorMessages", errorMessages);
				mav.addObject("errorMessages" ,errorMessages);
				mav.setViewName("/top");
				return mav;
			}
			
			userService.inquiryUser(user);
			return new ModelAndView("redirect:/confirm");


	}
	
}
