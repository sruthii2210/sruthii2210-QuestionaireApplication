package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionaire.entity.Teacher;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.ServiceException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.repository.TeacherRepository;
import com.questionaire.service.TeacherService;



@Service
public class TeacherServiceImpl implements TeacherService{
	@Autowired
	private TeacherRepository teacherRepositoryImpl;
	@Override
	public Teacher addTeacherDetails(Teacher teacherDetails) throws ServiceException {
		
		try {
			return teacherRepositoryImpl.addTeacherDetails(teacherDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public List<Teacher> getAllTeacherDetails() throws ServiceException {
		try {
			return teacherRepositoryImpl.getAllTeacherDetails();
		} catch (DatabaseException e) {
		throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public Teacher updateTeacherDetails(Long id, Teacher teacherDetails) throws ServiceException {
		try {
			return teacherRepositoryImpl.updateTeacherDetails(id,teacherDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public ResponseEntity<String> deleteTeacherDetails(Long id) {
		
		return teacherRepositoryImpl.deleteTeacherDetails(id);
	}
	@Override
	public Teacher getParticularTeacherDetails(Long id) throws ServiceException {
		
		try {
			return teacherRepositoryImpl.getParticularTeacherDetails(id);
		} catch (DatabaseException e) {
		throw new ServiceException(e.getMessage());
		}
	}
	
}
