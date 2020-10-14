package com.wellsfargo.fsd.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.fsd.spring.exception.InterviewException;
import com.wellsfargo.fsd.spring.model.AttendeeModel;
import com.wellsfargo.fsd.spring.model.InterviewModel;
import com.wellsfargo.fsd.spring.model.UserModel;
import com.wellsfargo.fsd.spring.service.InterviewService;
import com.wellsfargo.fsd.spring.service.UserService;


@RestController
@RequestMapping("/visitor")
public class VisitorRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private InterviewService interviewService;

	
	@GetMapping("/showUsers")
    public ResponseEntity<List<UserModel>> getAllUsers() throws InterviewException{
        return new ResponseEntity<List<UserModel>>(userService.getAllUsers(),HttpStatus.OK);
    }
	
	@GetMapping("/showUser/{id}")
	public ResponseEntity<UserModel> getUsers(@PathVariable("id")int userId) throws InterviewException{
		ResponseEntity<UserModel> resp=null;
		
		UserModel user = userService.getUser(userId);
		
		if(user!=null) {
			resp =new ResponseEntity<>(user,HttpStatus.OK);
		}else {
			resp =new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
		return resp;
	}
	
	@PostMapping("/addUser")
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel user,BindingResult result) throws InterviewException{
		if(result.hasErrors()) {
			throw new InterviewException(GlobalExceptionController.errMsgFrom(result));
		}		
		return new ResponseEntity<>(userService.addUser(user),HttpStatus.OK);
    }
	
	 @DeleteMapping("/deleteUser/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable("id")int userId) throws InterviewException{
	         userService.deleteUser(userId);        
	        return new ResponseEntity<>(HttpStatus.OK);
	}
	 
	 
	@GetMapping("/showInterviews")
    public ResponseEntity<List<InterviewModel>> getAllInterviewss() throws InterviewException{
        return new ResponseEntity<List<InterviewModel>>(interviewService.getAllInterviews(),HttpStatus.OK);
    }
	
	@GetMapping("/interviewCount")
    public ResponseEntity<String> getAllInterviewsCount() throws InterviewException{
        return new ResponseEntity<>(interviewService.getAllInterviewsCount(),HttpStatus.OK);
    }

	@GetMapping("/showInterview/{id}")
    public ResponseEntity<InterviewModel> getInterviews(@PathVariable("id")int interviewId) throws InterviewException{
        ResponseEntity<InterviewModel> resp=null;
        
        InterviewModel interview = interviewService.getInterviewById(interviewId);
        
        if(interview!=null) {
            resp =new ResponseEntity<>(interview,HttpStatus.OK);
        }else {
            resp =new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return resp;
    }
	
	@GetMapping("/showInterview/{InterviewName}/{InterviewerName}")
	public ResponseEntity<List<InterviewModel>> searchInterview(@PathVariable("InterviewName")String interviewName, @PathVariable("InterviewerName")String interviewerName){
		ResponseEntity<List<InterviewModel>> resp=null;
		List<InterviewModel> interview = interviewService.searchInterview(interviewName, interviewerName);
		
		if(interview!=null) {
			resp =new ResponseEntity<>(interview,HttpStatus.OK);
		}else {
			resp =new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return resp;
	}
		
	@PostMapping("/addInterview")
    public ResponseEntity<InterviewModel> addInterview(@RequestBody @Valid InterviewModel interview,BindingResult result) throws InterviewException{
        
		if(result.hasErrors()) {
			throw new InterviewException(GlobalExceptionController.errMsgFrom(result));
		}
		return new ResponseEntity<>(interviewService.addInterview(interview),HttpStatus.OK);
    }
	
	 @DeleteMapping("/deleteInterview/{id}")
	    public ResponseEntity<Void> deleteInterview(@PathVariable("id")int interviewId) throws InterviewException{
	        interviewService.deleteInterview(interviewId);        
	        return new ResponseEntity<>(HttpStatus.OK);
	 }
	 
	 @PutMapping("/interviewStatus/{id}/{status}")
	    public ResponseEntity<InterviewModel> updateInterviewStatus(@PathVariable("id")int interviewId,@PathVariable("status")String status) throws InterviewException{
	        return new ResponseEntity<>(interviewService.updateInterviewStatus(interviewId,status),HttpStatus.OK);
	 }
	
	 @GetMapping("/showAttendee/{id}")
	 	public ResponseEntity<List<UserModel>> showAttendees(@PathVariable("id")int interviewId) throws InterviewException{
		 	return new ResponseEntity<>(interviewService.showUsers(interviewId),HttpStatus.OK);
	 }
	
	 @PostMapping("/addAttendee")
	 	public ResponseEntity<String> addAttendee(@RequestBody @Valid AttendeeModel attendee,BindingResult result) throws InterviewException{
		 	
		 if(result.hasErrors()) {
				throw new InterviewException(GlobalExceptionController.errMsgFrom(result));
			}	 
		 return new ResponseEntity<>(interviewService.addAttendee(attendee),HttpStatus.OK);
    }
   
}
