package com.wellsfargo.fsd.spring.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserModel {

	@NotNull(message="User Id is Mandatory")
	private Integer userId;
	
	@NotNull(message = "First name is manditory")
	@NotBlank(message = "First name is manditory")
	@Size(min=5,max=30,message = "First name is expected to be 5 to 30 chars in length")
	private String firstName;
	
	@NotNull(message = "Last name is manditory")
	@NotBlank(message = "Last name is manditory")
	@Size(min=3,max=25,message = "Last name is expected to be 3 to 25 chars in length")
	private String lastName;
	
	@NotNull(message = "Email is manditory")
    @Email(message = "Please provide a valid email id")
	@Pattern(regexp=".+@.+\\..+",message = "Please provide a valid email id")
	private String email;

	@NotNull(message = "Mobile number is manditory")
	@NotBlank(message = "Mobile number is manditory")
	@Size(min=10,max=10,message = "Mobile number is expected to be equal to 10 chars in length")
	private Long mobile;
	
	public UserModel() {
		//left unimplemented
	}

	public UserModel(Integer userId,String firstName,String lastName, String email,Long mobile) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", mobile=" + mobile + "]";
	}
	
	
	
}
