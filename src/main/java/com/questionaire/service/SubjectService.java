package com.questionaire.service;

import java.util.List;

import com.questionaire.dto.Subject;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;

public interface SubjectService {

	String addSubject(String standard, Subject subject) throws ServiceException, NotFoundException;

	List<SubjectEntity> getSubject(String standard) throws ServiceException, NotFoundException;

	SubjectEntity updateSubject(String standard, String subCode, Subject subject)
			throws ServiceException, NotFoundException;

	String deleteSubject(String subCode) throws ServiceException, NotFoundException;

}
