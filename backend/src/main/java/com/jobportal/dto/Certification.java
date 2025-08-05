package com.jobportal.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certification {
	private String name;
    private String issuer;
    private LocalDateTime issueDate;
    private String certificateId;
}
