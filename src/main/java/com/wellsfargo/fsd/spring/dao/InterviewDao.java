package com.wellsfargo.fsd.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wellsfargo.fsd.spring.entity.InterviewEntity;

@Repository
public interface InterviewDao extends JpaRepository<InterviewEntity,Integer>{

	@Query("SELECT i FROM InterviewEntity i WHERE i.interviewId =:interviewid")
	List<InterviewEntity> findAllById(Integer interviewid);
	
	@Query("SELECT i FROM InterviewEntity i WHERE i.interviewName =:interviewName or i.interviewerName=:interviewerName")
	List<InterviewEntity> findByNameAndInterviewer(String interviewName, String interviewerName);

}
