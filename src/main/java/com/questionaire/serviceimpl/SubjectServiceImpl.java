package com.questionaire.serviceimpl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.questionaire.dto.Subject;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.ClassRepository;
import com.questionaire.repository.SubjectRepository;
import com.questionaire.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{

	public static Logger logger=Logger.getLogger(SubjectServiceImpl.class);
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private ClassRepository classRepository;
	@Override
	public String addSubject(Long roomNo,Subject subject) throws ServiceException, NotFoundException {
		try {
			classRepository.checkClassRoomNo(roomNo);
			logger.info("In addSubject in subjectServiceImp...");
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
			logger.info("In getSubject in subjectServiceImp...");
			subject = subjectRepository.getSubject(roomNo);
			
		} catch (DatabaseException e) {
		throw new ServiceException(e.getMessage());
		}
		return subject;
	}
	@Override
	public SubjectEntity updateSubject(Long roomNo, String subCode, Subject subject) throws ServiceException, NotFoundException {
		try {
			subjectRepository.checkSubjectRoom(roomNo, subCode);
			logger.info("In updateSubject in subjectServiceImp...");
			return subjectRepository.updateSubject(roomNo,subCode,subject);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public String deleteSubject(String subCode) throws ServiceException, NotFoundException {
		try {
			subjectRepository.checkSubject(subCode);
			logger.info("In deleteSubject in subjectServiceImp...");
			return subjectRepository.deleteSubject(subCode);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
