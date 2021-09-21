package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.questionaire.entity.Teacher;
import com.questionaire.exception.ServiceException;
import com.questionaire.exception.TeacherNotFoundException;

public interface TeacherService {
	Teacher addTeacherDetails(Teacher teacherDetails) throws ServiceException;
	List<Teacher> getAllTeacherDetails() throws ServiceException;
	Teacher updateTeacherDetails(Long id,Teacher teacherDetails) throws  ServiceException;
	ResponseEntity<String> deleteTeacherDetails(Long id);
	Teacher getParticularTeacherDetails(Long id) throws ServiceException;
}
