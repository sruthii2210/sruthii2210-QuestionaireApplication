package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.questionaire.entity.TeacherEntity;
import com.questionaire.entity.TeacherLogin;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.TeacherLoginRepository;
import com.questionaire.service.TeacherLoginService;

@Service
public class TeacherLoginServiceImpl implements TeacherLoginService {

	@Autowired
	private TeacherLoginRepository teacherLoginRepository;
	
	public TeacherLogin createLogin(Long id,TeacherLogin login) throws ServiceException
	{
		try {
			return teacherLoginRepository.createLogin(id,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public List<TeacherLogin> getDetails(Long id) throws ServiceException {
		
		List<TeacherLogin> teacher;
		try {
			teacher = teacherLoginRepository.getDetails(id);
		} catch (DatabaseException e) {
		throw new ServiceException(e.getMessage());
		}
		return teacher;
	}
	@Override
	public TeacherLogin updateLogin(Long id,TeacherLogin login) throws ServiceException {
		try {
			return teacherLoginRepository.updateLogin(id,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
