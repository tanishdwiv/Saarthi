package com.jobportal.entity;

import java.time.LocalDateTime;
import java.util.Base64;

import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.ApplicationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {
	private Long applicantId;
	private String name;
	private String email;
	private Long phone;
	private String website;
	private byte[] resume;
	private String coverLetter;
	private LocalDateTime timestamp;
	private ApplicationStatus applicationStatus;
	private LocalDateTime interviewTime;
	
	public ApplicantDTO toDTO() {
		return new ApplicantDTO(this.getApplicantId(), this.getName(), this.getEmail(), this.getPhone(), this.getWebsite(),this.getResume()!=null?Base64.getEncoder().encodeToString(this.getResume()):null, this.getCoverLetter(),this.getTimestamp(), this.getApplicationStatus(), this.interviewTime);
	}
}
