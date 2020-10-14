package com.wellsfargo.fsd.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class InterviewModel {

	@NotNull(message="Interview Id is Mandatory")
	private Integer interviewId;
	
	@NotNull(message = "Interviewer name is manditory")
	@NotBlank(message = "Interviewer name is manditory")
	@Size(min=5,max=30,message = "Interviewer name is expected to be 5 to 30 chars in length")
	private String interviewerName;

	@NotNull(message = "Interview name is manditory")
	@NotBlank(message = "Interview name is manditory")
	@Size(min=3,max=30,message = "Interview name is expected to be 3 to 30 chars in length")
	private String interviewName;

	@NotNull(message = "User skills is manditory")
	@NotBlank(message = "User skills is manditory")
	@Size(min=5,max=30,message = "User skills is expected to be 5 to 30 chars in length")
	private String userSkills;
	
	@NotNull(message = "Date is manditory")
	@PastOrPresent(message = "Date is expected to be past or present date")
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate date;
	
	@NotNull(message = "Time is manditory")
	@DateTimeFormat(iso=ISO.TIME)
	private LocalTime time;
	
	@NotNull(message = "Interview Status is manditory")
	@NotBlank(message = "Interview Status is manditory")
	@Size(min=5,max=100,message = "Interview Status is expected to be 5 to 100 chars in length")
	private String interviewStatus;
	
	@NotNull(message = "Remarks is manditory")
	@NotBlank(message = "Remarks is manditory")
	@Size(min=5,max=100,message = "Remarks is expected to be 5 to 100 chars in length")
	private String remarks;
	
	@Valid
	private List<UserModel> attendee;	
	
	public List<UserModel> getAttendee() {
		return attendee;
	}
	
	public void setAttendee(List<UserModel> attendees) {
		this.attendee = attendees;
	}
	
	public InterviewModel() {
		//left unimplemented
	}

	public InterviewModel(Integer interviewId,String interviewerName,String interviewName,String userSkills,LocalDate date,
			LocalTime time,String interviewStatus,String remarks) {
		super();
		this.interviewId = interviewId;
		this.interviewerName = interviewerName;
		this.interviewName = interviewName;
		this.userSkills = userSkills;
		this.date = date;
		this.time = time;
		this.interviewStatus = interviewStatus;
		this.remarks = remarks;
	}
	
	public InterviewModel(Integer interviewId,String interviewerName,String interviewName,String userSkills,
			LocalTime time, LocalDate date,String interviewStatus,String remarks,List<UserModel> attendees) {
		super();
		this.interviewId = interviewId;
		this.interviewerName = interviewerName;
		this.interviewName = interviewName;
		this.userSkills = userSkills;
		this.time = time;
		this.date = date;
		this.interviewStatus = interviewStatus;
		this.remarks = remarks;
		this.attendee = attendees;
	}


	public Integer getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getInterviewName() {
		return interviewName;
	}

	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}

	public String getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(String userSkills) {
		this.userSkills = userSkills;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

	@Override
	public String toString() {
		return "InterviewModel [interviewId=" + interviewId + ", interviewerName=" + interviewerName
				+ ", interviewName=" + interviewName + ", userSkills=" + userSkills + ", date=" + date + ", time="
				+ time + ", interviewStatus=" + interviewStatus + ", remarks=" + remarks + "]";
	}

	
}
