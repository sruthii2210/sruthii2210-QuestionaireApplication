package com.questionaire.service;

import java.util.List;

import com.questionaire.dto.TeacherLogin;
import com.questionaire.entity.TeacherLoginEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;

public interface TeacherLoginService {

	Long createLogin(Long id, TeacherLogin login) throws ServiceException, NotFoundException;

	List<TeacherLoginEntity> getDetails(Long id) throws ServiceException, NotFoundException;

	TeacherLoginEntity updateLogin(Long id, TeacherLogin login) throws ServiceException, NotFoundException;

}
