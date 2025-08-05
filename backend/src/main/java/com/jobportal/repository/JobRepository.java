package com.jobportal.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.jobportal.dto.ApplicationStatus;
import com.jobportal.entity.Job;

public interface JobRepository extends MongoRepository<Job, Long> {
	 @Query("{ 'applicants': { $elemMatch: { 'applicantId': ?0, 'applicationStatus': ?1 } } }")
    List<Job> findByApplicantIdAndApplicationStatus(Long applicantId, ApplicationStatus applicationStatus);
	 
	 List<Job> findByPostedBy(Long postedBy); 	
}
