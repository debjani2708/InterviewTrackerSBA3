package com.wellsfargo.fsd.spring.model;

import javax.validation.constraints.NotNull;

public class AttendeeModel {
	
	@NotNull(message="User Id is Mandatory")
	private Integer userId;
	
	@NotNull(message="Interview Id is Mandatory")
	private Integer interviewId;
	
	public AttendeeModel() {
		//left unimplemented
	}

	public AttendeeModel(Integer userId,Integer interviewId) {
		super();
		this.userId = userId;
		this.interviewId = interviewId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}

	@Override
	public String toString() {
		return "AttendeeModel [ userId=" + userId + ", interviewId=" + interviewId + "]";
	}
	
	
}
