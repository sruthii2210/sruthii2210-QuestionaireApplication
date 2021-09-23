package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.questionaire.dto.Teacher;
import com.questionaire.entity.TeacherEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.repository.TeacherRepository;
import com.questionaire.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public Long addTeacherDetails(Teacher teacherDetails) throws ServiceException {

		try {
			return teacherRepository.addTeacherDetails(teacherDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<TeacherEntity> getAllTeacherDetails() throws ServiceException {
		try {
			return teacherRepository.getAllTeacherDetails();
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public TeacherEntity updateTeacherDetails(Long id, Teacher teacherDetails)
			throws ServiceException, NotFoundException {
		try {
			teacherRepository.checkTeacher(id);
			return teacherRepository.updateTeacherDetails(id, teacherDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public String deleteTeacherDetails(Long id) throws ServiceException, TeacherNotFoundException {

		try {
			teacherRepository.checkTeacher(id);
			return teacherRepository.deleteTeacherDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public TeacherEntity getParticularTeacherDetails(Long id) throws ServiceException, NotFoundException {

		try {
			teacherRepository.checkTeacher(id);
			return teacherRepository.getParticularTeacherDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
