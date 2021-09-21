package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.questionaire.entity.Subject;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.SubjectRepository;
import com.questionaire.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectRepository subjectRepository;
	@Override
	public Subject addSubject(Long roomNo,Subject subject) throws ServiceException {
		try {
			return subjectRepository.addSubject(roomNo,subject);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public List<Subject> getSubject(Long roomNo) throws ServiceException
	{
		List<Subject> subject;
		try {
			subject = subjectRepository.getSubject(roomNo);
		} catch (DatabaseException e) {
		throw new ServiceException(e.getMessage());
		}
		return subject;
	}
	@Override
	public Subject updateSubject(Long roomNo, String subCode, Subject subject) throws ServiceException {
		try {
			return subjectRepository.updateSubject(roomNo,subCode,subject);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public String deleteSubject(String subCode) throws ServiceException {
		try {
			return subjectRepository.deleteSubject(subCode);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
