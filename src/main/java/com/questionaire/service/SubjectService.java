package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.dto.Subject;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.exception.ServiceException;


public interface SubjectService {

	 String addSubject(Long roomNo,Subject subject) throws ServiceException, NotFoundException;
	 List<SubjectEntity> getSubject(Long roomNo) throws ServiceException,NotFoundException;
	 SubjectEntity updateSubject(Long roomNo,String subCode,Subject subject) throws ServiceException, NotFoundException;
	 String deleteSubject(String subCode) throws ServiceException, NotFoundException;
	
	
}
