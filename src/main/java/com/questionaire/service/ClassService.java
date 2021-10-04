package com.questionaire.service;

import java.util.List;

import com.questionaire.dto.ClassDetails;
import com.questionaire.entity.ClassRoom;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;

public interface ClassService {

	Long addClass(ClassDetails classDetails) throws ServiceException, NotFoundException;

	List<ClassRoom> getClassDetails() throws ServiceException;

	ClassRoom updateClass(Long roomNo, ClassDetails classDetails) throws ServiceException, NotFoundException;

	ClassRoom getClass(String standard, String section) throws ServiceException;

	ClassRoom getClassDetails(Long roomNo) throws ServiceException, NotFoundException;

}
