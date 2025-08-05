package com.jobportal.service;

import java.util.List;

import com.jobportal.dto.ProfileDTO;
import com.jobportal.dto.UserDTO;
import com.jobportal.exception.JobPortalException;

public interface ProfileService {
	public Long createProfile(UserDTO userDTO) throws JobPortalException;

	public ProfileDTO getProfile(Long id) throws JobPortalException;

	public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException;

	public List<ProfileDTO> getAllProfiles() throws JobPortalException;
}
