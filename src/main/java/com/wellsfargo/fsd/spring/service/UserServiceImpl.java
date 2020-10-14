package com.wellsfargo.fsd.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.spring.dao.UserDao;
import com.wellsfargo.fsd.spring.entity.UserEntity;
import com.wellsfargo.fsd.spring.exception.InterviewException;
import com.wellsfargo.fsd.spring.model.UserModel;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userRepo;
	
	private UserEntity toEntity(UserModel model) {
		return new UserEntity(model.getUserId(),model.getFirstName(),model.getLastName(),
				model.getEmail(),model.getMobile());
	}
	
	private UserModel toModel(UserEntity entity) {
		return new UserModel(entity.getUserId(),entity.getFirstName(),entity.getLastName(),
				entity.getEmail(),entity.getMobile());
	}
	
	@Override
	@Transactional
	public UserModel addUser(UserModel user) throws InterviewException {
		 if(user!=null) {
	            if(userRepo.existsById(user.getUserId())) {
	                throw new InterviewException("User Id already used!");
	            }
	            
	            user = toModel(userRepo.save(toEntity(user)));
	        }
	        return user;
	}

	@Override
	@Transactional
	public boolean deleteUser(int userId) throws InterviewException {
		if(!userRepo.existsById(userId)) {
			throw new InterviewException("User Not Found");
		}
		
		userRepo.deleteById(userId);;
	return true;
	}
	
	@Override
	public UserModel getUser(int userId) throws InterviewException {
        UserEntity entity = userRepo.findById(userId).orElse(null);
        return entity!=null?toModel(entity):null;
	}

	@Override
	public List<UserModel> getAllUsers() throws InterviewException {
		List<UserEntity> entities= userRepo.findAll();
		List<UserModel> models=null;
		if(entities!=null && !(entities.isEmpty())) {
			models=entities.stream().map(e -> toModel(e)).collect(Collectors.toList());
		}
		return models;
	}

}
