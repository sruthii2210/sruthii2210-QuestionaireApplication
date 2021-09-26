package com.questionaire.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionaire.dto.TeacherLogin;
import com.questionaire.entity.TeacherLoginEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.TeacherLoginRepository;
import com.questionaire.repository.TeacherRepository;
import com.questionaire.service.TeacherLoginService;

@Service
public class TeacherLoginServiceImpl implements TeacherLoginService {

	@Autowired
	private TeacherLoginRepository teacherLoginRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	
	public Long createLogin(Long id,TeacherLogin login) throws ServiceException,NotFoundException
	{
		try {
			teacherRepository.checkTeacher(id);
			return teacherLoginRepository.createLogin(id,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public TeacherLoginEntity getDetails(Long id) throws ServiceException, NotFoundException {
		
		TeacherLoginEntity teacher;
		try {
			teacherRepository.checkTeacher(id);
			teacher = teacherLoginRepository.getDetails(id);
		} catch (DatabaseException e) {
		throw new ServiceException(e.getMessage());
		}
		return teacher;
	}
	@Override
	public TeacherLoginEntity updateLogin(Long id,TeacherLogin login) throws ServiceException, NotFoundException {
		try {
			teacherLoginRepository.checkAutoId(id);
			return teacherLoginRepository.updateLogin(id,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
