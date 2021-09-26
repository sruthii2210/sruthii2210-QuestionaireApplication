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
	public String addSubject(String standard,Subject subject) throws ServiceException, NotFoundException {
		try {
			
			classRepository.checkStandard(standard);
			logger.info("In addSubject in subjectServiceImp...");
			return subjectRepository.addSubject(standard,subject);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public List<SubjectEntity> getSubject(String standard) throws ServiceException, NotFoundException
	{
		List<SubjectEntity> subject;
		try {
			classRepository.checkStandard(standard);
			logger.info("In getSubject in subjectServiceImp...");
			subject = subjectRepository.getSubject(standard);
			
		} catch (DatabaseException e) {
		throw new ServiceException(e.getMessage());
		}
		return subject;
	}
	@Override
	public SubjectEntity updateSubject(String standard, String subCode, Subject subject) throws ServiceException, NotFoundException {
		try {
			classRepository.checkStandard(standard);
			
			subjectRepository.checkSubject(subCode);
			logger.info("In updateSubject in subjectServiceImp...");
			return subjectRepository.updateSubject(standard,subCode,subject);
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
