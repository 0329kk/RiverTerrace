package com.example.RiverTerrace.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.RiverTerrace.dto.PlanDto;
import com.example.RiverTerrace.dto.ReservationDto;
import com.example.RiverTerrace.entity.Plan;
import com.example.RiverTerrace.entity.Reservation;
import com.example.RiverTerrace.service.PlanService;
import com.example.RiverTerrace.service.ReservationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;

	@Autowired
	PlanService planService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/reservation")
	public ModelAndView reservation() {
		ModelAndView mav = new ModelAndView();
		List<Plan> planList =  reservationService.getAll();
		mav.addObject("planList", planList);
		mav.setViewName("/reservation");
		
		return mav;
		
	}
	
	@GetMapping("/reservationInfo/{id}")
	public ModelAndView reservationInfo(PlanDto planDto, @PathVariable("id") String id) throws IOException{
		ModelAndView mav = new ModelAndView();
		Reservation reservation = new Reservation();
		Plan planList = planService.findPlan(Integer.parseInt(id));
		List<String> numPepole = new ArrayList<>();
		numPepole.add("1");
		numPepole.add("2");
		numPepole.add("3");
		numPepole.add("4");
		numPepole.add("5");
		numPepole.add("6");
		
		String fileImgLogo = "src/main/resources/static/image/logo.png";
		String fileImgTwitter = "src/main/resources/static/image/twitter.png";
		String fileImgYoutube = "src/main/resources/static/image/youtube.png";
		String fileImgInstagram = "src/main/resources/static/image/instagram.png";
		String fileImgFacebook = "src/main/resources/static/image/facebook.png";
		
		Path logoImagePath = Paths.get(fileImgLogo);
		Path twitterImagePath = Paths.get(fileImgTwitter);
		Path youtubeImagePath = Paths.get(fileImgYoutube);
		Path instagramImagePath = Paths.get(fileImgInstagram);
		Path facebookImagePath = Paths.get(fileImgFacebook);
		
		byte[] logoImageBytes = Files.readAllBytes(logoImagePath);
		byte[] twitterImageBytes = Files.readAllBytes(twitterImagePath);
		byte[] youtubeImageBytes = Files.readAllBytes(youtubeImagePath);
		byte[] instagramImageBytes = Files.readAllBytes(instagramImagePath);
		byte[] facebookImageBytes = Files.readAllBytes(facebookImagePath);
		
		String base64LogoImage = java.util.Base64.getEncoder().encodeToString(logoImageBytes);
		String base64TwitterImage = java.util.Base64.getEncoder().encodeToString(twitterImageBytes);
		String base64YoutubeImage = java.util.Base64.getEncoder().encodeToString(youtubeImageBytes);
		String base64InstagramImage = java.util.Base64.getEncoder().encodeToString(instagramImageBytes);
		String base64FacebookImage = java.util.Base64.getEncoder().encodeToString(facebookImageBytes);
		
		mav.addObject("base64LogoImage", base64LogoImage);
		mav.addObject("base64TwitterImage", base64TwitterImage);
		mav.addObject("base64YoutubeImage", base64YoutubeImage);
		mav.addObject("base64InstagramImage", base64InstagramImage);
		mav.addObject("base64FacebookImage", base64FacebookImage);

		planDto.setPlanname(planList.getPlanname());
		planDto.setNumpepole(planList.getNumpepole());
		planDto.setPrice(planList.getPrice());
		planDto.setId(Integer.parseInt(id));
		mav.addObject("planDto", planDto);
		mav.addObject("numPepoleList", numPepole);
		mav.addObject("formModel", reservation);
		mav.setViewName("/reservInput");
		
		return mav;
	}
		
	@PostMapping("/reservInput/{id}")
	public ModelAndView reservationContent(@ModelAttribute(name = "formModel") ReservationDto reservationDto, 
			@RequestParam("planId") String planId, @RequestParam("planName") String planName, @RequestParam("planPrice") String planPrice, @PathVariable Integer id) throws ParseException {
		ModelAndView mav = new ModelAndView();     
		Reservation reservation = new Reservation();
		List<String> errorMessages = new ArrayList<>();
		String theAlphaNumericS;
        StringBuilder builder;
        theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"; 
        builder = new StringBuilder(10); 

        for (int m = 0; m < 10; m++) {
            int myindex 
                = (int)(theAlphaNumericS.length() 
                        * Math.random()); 
            builder.append(theAlphaNumericS 
                        .charAt(myindex)); 
        }
		List<String> numPepoleList = new ArrayList<>();
		numPepoleList.add("1");
		numPepoleList.add("2");
		numPepoleList.add("3");
		numPepoleList.add("4");
		numPepoleList.add("5");
		numPepoleList.add("6");
        
        String reservationid = builder.toString();
        PlanDto planDto = new PlanDto();
        planDto.setId(Integer.parseInt(planId));
        planDto.setPlanname(planName);
        planDto.setPrice(Integer.parseInt(planPrice));
        reservationDto.setPlanId(Integer.parseInt(planId));
        reservationDto.setPlanname(planName);
        reservationDto.setPrice(Integer.parseInt(planPrice));
        reservation.setPlanname(reservationDto.getPlanname());
        reservation.setPlanid(reservationDto.getPlanId());
        reservation.setPrice(reservationDto.getPrice());
        reservation.setName(reservationDto.getName());
        reservation.setFurigana(reservationDto.getFurigana());
        reservation.setPhone_number(reservationDto.getPhone_number());
        reservation.setReservationid(reservationid);
        reservation.setNumPepole(reservationDto.getNumPepole());
        reservation.setEmail(reservationDto.getEmail());
//        System.out.println("reservationDto.getNumPepole(): " + reservationDto.getNumPepole());
        if (!reservation.getName().isEmpty() && !reservation.getFurigana().isEmpty() && !reservation.getPhone_number().isEmpty() && !reservation.getEmail().isEmpty() && !reservation.getPlanname().isEmpty() && !reservation.getNumPepole().isEmpty() && !reservationDto.getStayDate().isEmpty()) {
            Locale locale = new Locale("ja", "JP");
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy年MM月dd日", locale);
            Date stayDate = sdFormat.parse(reservationDto.getStayDate());
            reservation.setStayDate(stayDate);
        	session.invalidate();
	        reservationService.saverReservationUser(reservation);
	        mav.addObject("reservations", reservation);
			mav.setViewName("/confirmReservation");
			return mav;
		} else {	        
	        errorMessages.add("必須項目は必ず入力してください");
			session.setAttribute("errorMessages", errorMessages);
			mav.addObject("errorMessages", errorMessages);
			mav.addObject("stayDate", reservationDto.getStayDate());
			mav.addObject("formModel", reservation);
			mav.addObject("planDto", planDto);
			mav.addObject("numPepoleList", numPepoleList);
			mav.setViewName("/reservInput");
			return mav;
		}

	}
	
	@GetMapping("/reservationEdit/{id}")
	public ModelAndView reservationEdit(@PathVariable("id") String id) throws IOException {
		ModelAndView mav = new ModelAndView();
//		Reservation reservationUser = reservationService.findByReservationid(reservationid);
		Integer idInteger = Integer.parseInt(id);
		Reservation reservationUser = reservationService.findByReservationUserId(idInteger);
		List<String> numPepole = new ArrayList<>();
		numPepole.add("1");
		numPepole.add("2");
		numPepole.add("3");
		numPepole.add("4");
		numPepole.add("5");
		numPepole.add("6");
		
		String fileImgLogo = "src/main/resources/static/image/logo.png";
		String fileImgTwitter = "src/main/resources/static/image/twitter.png";
		String fileImgYoutube = "src/main/resources/static/image/youtube.png";
		String fileImgInstagram = "src/main/resources/static/image/instagram.png";
		String fileImgFacebook = "src/main/resources/static/image/facebook.png";
		
		Path logoImagePath = Paths.get(fileImgLogo);
		Path twitterImagePath = Paths.get(fileImgTwitter);
		Path youtubeImagePath = Paths.get(fileImgYoutube);
		Path instagramImagePath = Paths.get(fileImgInstagram);
		Path facebookImagePath = Paths.get(fileImgFacebook);
		
		byte[] logoImageBytes = Files.readAllBytes(logoImagePath);
		byte[] twitterImageBytes = Files.readAllBytes(twitterImagePath);
		byte[] youtubeImageBytes = Files.readAllBytes(youtubeImagePath);
		byte[] instagramImageBytes = Files.readAllBytes(instagramImagePath);
		byte[] facebookImageBytes = Files.readAllBytes(facebookImagePath);
		
		String base64LogoImage = java.util.Base64.getEncoder().encodeToString(logoImageBytes);
		String base64TwitterImage = java.util.Base64.getEncoder().encodeToString(twitterImageBytes);
		String base64YoutubeImage = java.util.Base64.getEncoder().encodeToString(youtubeImageBytes);
		String base64InstagramImage = java.util.Base64.getEncoder().encodeToString(instagramImageBytes);
		String base64FacebookImage = java.util.Base64.getEncoder().encodeToString(facebookImageBytes);
		
		mav.addObject("base64LogoImage", base64LogoImage);
		mav.addObject("base64TwitterImage", base64TwitterImage);
		mav.addObject("base64YoutubeImage", base64YoutubeImage);
		mav.addObject("base64InstagramImage", base64InstagramImage);
		mav.addObject("base64FacebookImage", base64FacebookImage);
		
		List<Plan> planList =  reservationService.getAll();
		List<String> planNameList = new ArrayList<>();
		for (int i = 0; i < planList.size(); i++) {
			String planName = planList.get(i).getPlanname();
			planNameList.add(planName);
		}
		List<String> errorMessages = (List<String>) session.getAttribute("errorMessages");
		List<String> blank = new ArrayList<>();
		session.setAttribute("errorMessages", blank);
		
		Locale locale = new Locale("ja", "JP");
        SimpleDateFormat sdFormater = new SimpleDateFormat("yyyy年MM月dd日", locale);
        String stayDate = sdFormater.format(reservationUser.getStayDate());
		mav.addObject("errorMessages", errorMessages);
        mav.addObject("planList", planNameList);
		mav.addObject("numPepoleList", numPepole);
		mav.addObject("stayDate", stayDate);
		mav.addObject("formModel", reservationUser);
		mav.setViewName("/reservationEdit");
		return mav;
	}
	
	@GetMapping("/reservationEditLogin")
	public ModelAndView reservationEditLogin() {
		ModelAndView mav = new ModelAndView();
		Reservation reservation = new Reservation();
		List<String> errorMessages = (List<String>) session.getAttribute("errorMessages");
		List<String> blank = new ArrayList<>();
		session.setAttribute("errorMessages", blank);
		mav.setViewName("/reservationEditLogin");
		mav.addObject("errorMessages", errorMessages);
		mav.addObject("formModel", reservation);
		return mav;
		
	}
	
	@PostMapping("/reservationEditLogin")
	public ModelAndView reservationEditLoginUser(@ModelAttribute(name = "formModel") Reservation reservation) {
		ModelAndView mav = new ModelAndView();
		List<String> errorMessages = new ArrayList<>();
		if (!reservation.getReservationid().isEmpty()) {
			Reservation reservationUser = reservationService.findByReservationid(reservation.getReservationid());
			if (reservationUser.getReservationid().equals(reservation.getReservationid()) && reservationUser.getName().equals(reservation.getName()) && reservationUser.getEmail().equals(reservation.getEmail())) {
				mav.addObject("id", reservationUser.getId());
				mav.setViewName("redirect:/reservationEdit/{id}");
				return mav;

			} else {
				errorMessages.add("入力内容が間違っています");
				session.setAttribute("errorMessages", errorMessages);
				return new ModelAndView("redirect:/reservationEditLogin");
			}
		} else {
			errorMessages.add("入力内容が間違っています");
			session.setAttribute("errorMessages", errorMessages);
			return new ModelAndView("redirect:/reservationEditLogin");
		}
	}
	
	//予約内容編集処理と削除ボタン処理を記載
	@PostMapping("/reservationEdit/{id}")
	public ModelAndView rvetionEdit(@ModelAttribute(name = "formModel") ReservationDto reservationDto, @RequestParam(name = "stayDate") String stayDate, @PathVariable Integer id) throws ParseException {
		ModelAndView mav = new ModelAndView();
		List<String> numPepoleList = new ArrayList<>();
		numPepoleList.add("1");
		numPepoleList.add("2");
		numPepoleList.add("3");
		numPepoleList.add("4");
		numPepoleList.add("5");
		numPepoleList.add("6");
		List<Plan> planList =  reservationService.getAll();
		List<String> planNameList = new ArrayList<>();
		for (int i = 0; i < planList.size(); i++) {
			String planName = planList.get(i).getPlanname();
			planNameList.add(planName);
		}
		
		List<String> errorMessages = new ArrayList<>();
		Reservation reservation = new Reservation();
        Reservation reservUsers = reservationService.findByReservationUserId(id);
        Plan planLists = planService.findByPlanNameLike(reservationDto.getPlanname());
        reservation.setId(reservUsers.getId());
        reservation.setPlanid(planLists.getId());
        reservation.setPlanname(reservationDto.getPlanname());
        reservation.setPrice(planLists.getPrice());
        reservation.setReservationid(reservUsers.getReservationid());
		reservation.setName(reservationDto.getName());
		reservation.setFurigana(reservationDto.getFurigana());
		reservation.setPhone_number(reservationDto.getPhone_number());
		reservation.setEmail(reservationDto.getEmail());
		reservation.setNumPepole(reservationDto.getNumPepole());
		if (!reservation.getName().isEmpty() && !reservation.getFurigana().isEmpty() && !reservation.getPhone_number().isEmpty() && !reservation.getEmail().isEmpty() && !reservation.getPlanname().isEmpty() && !reservation.getNumPepole().isEmpty() && !stayDate.isEmpty()) {
	        Locale locale = new Locale("ja", "JP");
	        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy年MM月dd日", locale);
	        Date formatDateStayDate = sdFormat.parse(stayDate);
			reservation.setStayDate(formatDateStayDate);
			reservationService.saverReservationUser(reservation);
			session.invalidate();
			return new ModelAndView("redirect:/reservation");
		} else {
	        if (!stayDate.isEmpty()) {
		        mav.addObject("stayDate", stayDate);
			}
	        
	        errorMessages.add("必須項目は必ず入力してください");
			session.setAttribute("errorMessages", errorMessages);
			mav.addObject("errorMessages", errorMessages);
			mav.addObject("formModel", reservation);
	        mav.addObject("planList", planNameList);
			mav.addObject("numPepoleList", numPepoleList);
			mav.setViewName("/reservationEdit");
			return mav;
		}
	}
	
	@PostMapping("/reservationDelete/{id}")
	public ModelAndView reationDelete(@PathVariable Integer id) {
		reservationService.reservationDeleteUser(id);
		return new ModelAndView("redirect:/reservation");
	}
	
}
