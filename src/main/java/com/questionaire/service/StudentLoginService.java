package com.questionaire.service;

import com.questionaire.dto.StudentLogin;
import com.questionaire.entity.StudentLoginEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;

public interface StudentLoginService {

	Long createLogin(Long id, StudentLogin login) throws ServiceException, NotFoundException;

	StudentLoginEntity getDetails(Long id) throws ServiceException, NotFoundException;

	StudentLoginEntity updateLogin(Long id, StudentLogin login) throws ServiceException, NotFoundException;
}
