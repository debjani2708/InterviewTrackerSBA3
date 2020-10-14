package com.wellsfargo.fsd.spring.service;

import java.util.List;

import com.wellsfargo.fsd.spring.exception.InterviewException;
import com.wellsfargo.fsd.spring.model.AttendeeModel;
import com.wellsfargo.fsd.spring.model.InterviewModel;
import com.wellsfargo.fsd.spring.model.UserModel;

public interface InterviewService {

	public InterviewModel addInterview(InterviewModel interview) throws InterviewException;
	public boolean deleteInterview(int interviewId) throws InterviewException;
	public List<InterviewModel> getAllInterviews() throws InterviewException;	
	public InterviewModel getInterviewById(int interviewId) throws InterviewException;
	
	public InterviewModel updateInterviewStatus(int interviewId,String status) throws InterviewException;
	public String getAllInterviewsCount() throws InterviewException;
	List<InterviewModel> searchInterview(String interviewName, String interviewerName);
		
	List<UserModel> showUsers(int interviewId) throws InterviewException;

	String addAttendee(AttendeeModel attendee) throws InterviewException;
}
