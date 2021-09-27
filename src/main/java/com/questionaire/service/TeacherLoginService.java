package com.questionaire.service;

import com.questionaire.dto.TeacherLogin;
import com.questionaire.entity.TeacherLoginEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;

public interface TeacherLoginService {

	Long createLogin(Long id, TeacherLogin login) throws ServiceException, NotFoundException;

	TeacherLoginEntity getDetails(Long id) throws ServiceException, NotFoundException;

	TeacherLoginEntity updateLogin(Long id, TeacherLogin login) throws ServiceException, NotFoundException;

}
