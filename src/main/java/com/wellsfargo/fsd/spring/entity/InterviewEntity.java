package com.wellsfargo.fsd.spring.entity;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="InterviewDetails")
public class InterviewEntity implements Serializable,Comparable<InterviewEntity>{

	@Id
	@Column(name="interviewid",unique=true)
	private Integer interviewId;
	
	@Column(name="interviewerName")
	private String interviewerName;
	
	@Column(name="interviewName")
	private String interviewName;
	
	@Column(name="userskills")
	private String userSkills;
	
	@Column(name="date")
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate date;
	
	@Column(name="time")
	@DateTimeFormat(iso=ISO.TIME)
	private LocalTime time;
	
	@Column(name="interviewstatus")
	private String interviewStatus;
	
	@Column(name="remarks")
	private String remarks;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Attendee_Details", 
      joinColumns = @JoinColumn(name = "interviewId", referencedColumnName = "interviewid"), 
      inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "uid"))
	private List<UserEntity> attendees;
	
	public InterviewEntity() {
		//left unimplemented
	}

	public InterviewEntity(Integer interviewId, String interviewerName, String interviewName, String userSkills,
			LocalDate date, LocalTime time, String interviewStatus, String remarks) {
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
	
	public InterviewEntity(Integer interviewId, String interviewerName, String interviewName, String userSkills,
			LocalDate date, LocalTime time, String interviewStatus, String remarks,List<UserEntity> attendees) {
		super();
		this.interviewId = interviewId;
		this.interviewerName = interviewerName;
		this.interviewName = interviewName;
		this.userSkills = userSkills;
		this.date = date;
		this.time = time;
		this.interviewStatus = interviewStatus;
		this.remarks = remarks;
		this.attendees=attendees;
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

	public List<UserEntity> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<UserEntity> attendees) {
		this.attendees = attendees;
	}

	@Override
	public String toString() {
		return "InterviewEntity [interviewId=" + interviewId + ", interviewerName=" + interviewerName
				+ ", interviewName=" + interviewName + ", userSkills=" + userSkills + ", date=" + date + ", time="
				+ time + ", interviewStatus=" + interviewStatus + ", remarks=" + remarks + "]";
	}
	
	@Override
	public int compareTo(InterviewEntity o) {
		return 0;
	}

}