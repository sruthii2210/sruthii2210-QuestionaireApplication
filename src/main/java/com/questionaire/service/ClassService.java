package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.questionaire.entity.ClassRoom;
import com.questionaire.exception.ServiceException;

public interface ClassService {
	
	ClassRoom addClass(ClassRoom classDetails) throws ServiceException;
	List<ClassRoom> getClassDetails() throws ServiceException;
	ClassRoom updateClass(Long roomNo,ClassRoom classDetails) throws ServiceException;
	ClassRoom getClass(String standard,String section) throws ServiceException;
	
	
}
