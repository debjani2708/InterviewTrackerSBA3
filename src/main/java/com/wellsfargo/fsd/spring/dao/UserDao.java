package com.wellsfargo.fsd.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellsfargo.fsd.spring.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity,Integer>{
	
}
