package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.TeacherLogin;
import com.questionaire.exception.ServiceException;

public interface TeacherLoginService {

	TeacherLogin createLogin(Long id, TeacherLogin login) throws ServiceException;

	List<TeacherLogin> getDetails(Long id) throws ServiceException;

	TeacherLogin updateLogin(Long id, TeacherLogin login) throws ServiceException;

}
