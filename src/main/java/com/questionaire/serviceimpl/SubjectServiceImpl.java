package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.questionaire.dto.Subject;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.repository.ClassRepository;
import com.questionaire.repository.SubjectRepository;
import com.questionaire.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private ClassRepository classRepository;
	@Override
	public String addSubject(Long roomNo,Subject subject) throws ServiceException, NotFoundException {
		try {
			classRepository.checkClassRoomNo(roomNo);
			return subjectRepository.addSubject(roomNo,subject);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public List<SubjectEntity> getSubject(Long roomNo) throws ServiceException, NotFoundException
	{
		List<SubjectEntity> subject;
		try {
			classRepository.checkClassRoomNo(roomNo);
			subject = subjectRepository.getSubject(roomNo);
			
		} catch (DatabaseException e) {
		throw new ServiceException(e.getMessage());
		}
		return subject;
	}
	@Override
	public SubjectEntity updateSubject(Long roomNo, String subCode, Subject subject) throws ServiceException, NotFoundException {
		try {
			classRepository.checkClassRoomNo(roomNo);
			subjectRepository.checkSubject(subCode);
			return subjectRepository.updateSubject(roomNo,subCode,subject);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public String deleteSubject(String subCode) throws ServiceException, NotFoundException {
		try {
			subjectRepository.checkSubject(subCode);
			return subjectRepository.deleteSubject(subCode);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
