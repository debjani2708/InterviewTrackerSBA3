package com.wellsfargo.fsd.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.spring.dao.InterviewDao;
import com.wellsfargo.fsd.spring.dao.UserDao;
import com.wellsfargo.fsd.spring.entity.InterviewEntity;
import com.wellsfargo.fsd.spring.entity.UserEntity;
import com.wellsfargo.fsd.spring.exception.InterviewException;
import com.wellsfargo.fsd.spring.model.AttendeeModel;
import com.wellsfargo.fsd.spring.model.InterviewModel;
import com.wellsfargo.fsd.spring.model.UserModel;

@Service
public class InterviewServiceImpl implements InterviewService{

	@Autowired
	private InterviewDao interviewRepo;
	
	@Autowired
	private UserDao userRepo;
	
	private InterviewEntity toInterviewEntity(InterviewModel model) {
		if(model.getAttendee()==null)
			return new InterviewEntity(model.getInterviewId(), model.getInterviewerName(), model.getInterviewName(), model.getUserSkills(), model.getDate(), model.getTime(), model.getInterviewStatus(), model.getRemarks());
		else
			return new InterviewEntity(model.getInterviewId(), model.getInterviewerName(), model.getInterviewName(), model.getUserSkills(), model.getDate(), model.getTime(), model.getInterviewStatus(), model.getRemarks(),toUserEntities(model.getAttendee()));
	}
	
	private InterviewModel toInterviewModel(InterviewEntity entity) {		
		if(entity.getAttendees()==null)
			return new InterviewModel(entity.getInterviewId(), entity.getInterviewerName(), entity.getInterviewName(), entity.getUserSkills(), entity.getDate(), entity.getTime(), entity.getInterviewStatus(), entity.getRemarks());
		else
			return new InterviewModel(entity.getInterviewId(), entity.getInterviewerName(), entity.getInterviewName(), entity.getUserSkills(), entity.getTime(), entity.getDate(), entity.getInterviewStatus(), entity.getRemarks(), toUserModels(entity.getAttendees()));
	}
	
	private List<UserEntity> toUserEntities(List<UserModel> userModels) {
		List<UserEntity> entities=null;
		entities = userModels.stream().map(e -> toUserEntity(e)).collect(Collectors.toList());
		return entities;
	}
	
	private UserEntity toUserEntity(UserModel model) {
		return new UserEntity(model.getUserId(), model.getFirstName(), model.getLastName(),model.getEmail(), model.getMobile());
	}
	
	private List<UserModel> toUserModels(List<UserEntity> userEntities) {
		List<UserModel> models=null;
		models = userEntities.stream().map(e -> toUserModel(e)).collect(Collectors.toList());
		return models;
	}
	
	private UserModel toUserModel(UserEntity entity) {
		return new UserModel(entity.getUserId(), entity.getFirstName(), entity.getLastName(),entity.getEmail(), entity.getMobile());
	}
	
	private InterviewModel getInterviewModel(InterviewEntity entity) {
		return new InterviewModel(entity.getInterviewId(), entity.getInterviewerName(), entity.getInterviewName(), entity.getUserSkills(), entity.getDate(), entity.getTime(), entity.getInterviewStatus(), entity.getRemarks());
	}
	
	@Override
	@Transactional
	public InterviewModel addInterview(InterviewModel interview) throws InterviewException {
		 if(interview!=null) {
	            if(interviewRepo.existsById(interview.getInterviewId())) {
	                throw new InterviewException("Interview Id already used!");
	            }
	            
	            interview = toInterviewModel(interviewRepo.save(toInterviewEntity(interview)));
	        }
	        return interview;
	}

	@Override
	public InterviewModel updateInterviewStatus(int interviewId,String status) throws InterviewException {
	
            if(!interviewRepo.existsById(interviewId)) {
                throw new InterviewException("Interview Id Not Found");
            }
            
            InterviewModel interview=getInterviewById(interviewId);
            interview.setInterviewStatus(status);
            interviewRepo.save(toInterviewEntity(interview));
    		return getInterviewModel(toInterviewEntity(interview));
	}
	
	@Override
	public List<InterviewModel> searchInterview(String interviewName, String interviewerName) {
		List<InterviewEntity> entities= interviewRepo.findByNameAndInterviewer(interviewName, interviewerName);
		List<InterviewModel> models=null;
		if(entities!=null && !entities.isEmpty()) {
			models = entities.stream().map(e -> getInterviewModel(e)).collect(Collectors.toList());
		}
		return models;
	}
	
	@Override
	@Transactional
	public boolean deleteInterview(int interviewId) throws InterviewException {
		if(!interviewRepo.existsById(interviewId)) {
			throw new InterviewException("Interview Not Found");
		}
		
		interviewRepo.deleteById(interviewId);;
	return true;
	}

	@Override
	public List<InterviewModel> getAllInterviews() throws InterviewException {
		List<InterviewEntity> entities= interviewRepo.findAll();
		List<InterviewModel> models=null;
		if(entities!=null && !(entities.isEmpty())) {
			models=entities.stream().map(e -> getInterviewModel(e)).collect(Collectors.toList());
		}
		return models;
	}

	@Override
	public InterviewModel getInterviewById(int interviewId) throws InterviewException {
		InterviewEntity entity = interviewRepo.findById(interviewId).orElse(null);
	        return entity!=null?toInterviewModel(entity):null;
	}

	@Override
	public String getAllInterviewsCount() throws InterviewException {
		List<InterviewEntity> entities = interviewRepo.findAll();
		if(entities!=null) {
        return "Total number of interviews till now : " + entities.size();
		}
		return "No interviews present as of now";
		
	}

	@Override
	public List<UserModel> showUsers(int interviewId) throws InterviewException {
		if(!interviewRepo.existsById(interviewId)) {
            throw new InterviewException("Interview Id Not Found!");
        }
		return toUserModels(interviewRepo.findById(interviewId).orElse(null).getAttendees());
	}
	
	public UserModel getUserById(int userId) {
	    UserEntity entity = userRepo.findById(userId).orElse(null);
	    return entity!=null?toUserModel(entity):null;
	}
	
	@Override
	@Transactional
	public String addAttendee(AttendeeModel attendee) throws InterviewException {
		if(attendee!=null) {
			if(!userRepo.existsById(attendee.getUserId())) {
				throw new InterviewException("User Id Not Found");
			}
	        if(!interviewRepo.existsById(attendee.getInterviewId())) {
	            throw new InterviewException("Interview Id Not Found!");
	        }
	        InterviewModel interview = getInterviewById(attendee.getInterviewId());
	        System.out.println("Print"+interview.getAttendee());
	        System.out.println("userid"+attendee.getUserId());
	        for(UserModel user: interview.getAttendee()) {
	        	if(user.getUserId() == attendee.getUserId()) {
	        		throw new InterviewException("User Id already exists on Interview!");
	        	}
	        }
	        List<UserModel> users=interview.getAttendee();	        
	        users.add(getUserById(attendee.getUserId()));
	        interview.setAttendee(users);
	        interviewRepo.save(toInterviewEntity(interview));
	        return "User with Id: " +  getUserById(attendee.getUserId()) + " added successfully to Interview";
		}
		return "Error adding User with Id: " +  attendee.getUserId();
	}

}
