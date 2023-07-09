package com.example.RiverTerrace.dto;

public class ReservationDto {	
	private String name;
	
	private String furigana;
	
	
	private String email;
	
	private String phone_number;
	
	private String stayDate;
	
	private Integer planId;
	
	private String planname;
	
	private String numPepole;
	
	private Integer price;

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public String getNumPepole() {
		return numPepole;
	}

	public void setNumPepole(String numPepole) {
		this.numPepole = numPepole;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFurigana() {
		return furigana;
	}

	public void setFurigana(String furigana) {
		this.furigana = furigana;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getStayDate() {
		return stayDate;
	}

	public void setStayDate(String stayDate) {
		this.stayDate = stayDate;
	}
	
}
