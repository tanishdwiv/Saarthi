package com.jobportal.dto;


import java.time.LocalDateTime;
import java.util.Base64;

import com.jobportal.entity.Applicant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDTO {
	private Long applicantId;
	private String name;
	private String email;
	private Long phone;
	private String website;
	private String resume;
	private String coverLetter;
	private LocalDateTime timestamp;
	private ApplicationStatus applicationStatus;
	private LocalDateTime interviewTime;
	
	public Applicant toEntity() {
		return new Applicant(this.getApplicantId(), this.getName(), this.getEmail(), this.getPhone(), this.getWebsite(), this.getResume()!=null?Base64.getDecoder().decode(this.getResume()):null, this.getCoverLetter(),this.getTimestamp(), this.getApplicationStatus(), this.interviewTime);
	}
}
