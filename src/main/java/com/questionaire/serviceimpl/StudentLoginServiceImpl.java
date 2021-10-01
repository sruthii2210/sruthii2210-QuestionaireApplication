package com.questionaire.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionaire.dto.StudentLogin;
import com.questionaire.entity.StudentLoginEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.StudentLoginRepository;
import com.questionaire.repository.StudentRepository;
import com.questionaire.service.StudentLoginService;

@Service
public class StudentLoginServiceImpl implements StudentLoginService {

	@Autowired
	private StudentLoginRepository studentLoginRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Long createLogin(Long id, StudentLogin login) throws ServiceException, NotFoundException {
		try {
			studentRepository.checkStudent(id);
			return studentLoginRepository.createLogin(id, login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public StudentLoginEntity getDetails(Long id) throws NotFoundException, ServiceException {

		StudentLoginEntity student;
		try {
			studentRepository.checkStudent(id);
			student = studentLoginRepository.getDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		return student;
	}


	public StudentLoginEntity updateLogin(Long id,StudentLogin login) throws ServiceException, NotFoundException {
		try {
			studentLoginRepository.checkAutoId(id);
			return studentLoginRepository.updateLogin(id, login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	
}
