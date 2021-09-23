package com.questionaire.service;

import java.util.List;

import com.questionaire.dto.Teacher;
import com.questionaire.entity.TeacherEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.exception.TeacherNotFoundException;

public interface TeacherService {
	Long addTeacherDetails(Teacher teacherDetails) throws ServiceException;

	List<TeacherEntity> getAllTeacherDetails() throws ServiceException;

	TeacherEntity updateTeacherDetails(Long id, Teacher teacherDetails) throws ServiceException, NotFoundException;

	String deleteTeacherDetails(Long id) throws ServiceException, TeacherNotFoundException;

	TeacherEntity getParticularTeacherDetails(Long id) throws ServiceException, NotFoundException;
}
