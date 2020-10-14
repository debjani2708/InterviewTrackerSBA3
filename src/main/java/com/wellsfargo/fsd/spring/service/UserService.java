package com.wellsfargo.fsd.spring.service;

import java.util.List;

import com.wellsfargo.fsd.spring.exception.InterviewException;
import com.wellsfargo.fsd.spring.model.UserModel;


public interface UserService {
	
	public UserModel addUser(UserModel user) throws InterviewException;
	public UserModel getUser(int userId) throws InterviewException;
    
    boolean deleteUser(int userId) throws InterviewException;
    
    public List<UserModel> getAllUsers() throws InterviewException;

}
