package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.Subject;
import com.questionaire.exception.ServiceException;


public interface SubjectService {

	 Subject addSubject(Long roomNo,Subject subject) throws ServiceException;
	 List<Subject> getSubject(Long roomNo) throws ServiceException;
	 Subject updateSubject(Long roomNo,String subCode,Subject subject) throws ServiceException;
	 String deleteSubject(String subCode) throws ServiceException;
	
	
}
