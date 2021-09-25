package com.questionaire.repository;

import java.util.List;

import com.questionaire.dto.TeacherLogin;
import com.questionaire.entity.TeacherLoginEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.IdNotFoundException;

public interface TeacherLoginRepository {

	void checkAutoId(Long id) throws IdNotFoundException;

	Long createLogin(Long id, TeacherLogin login) throws DatabaseException;

	List<TeacherLoginEntity> getDetails(Long id) throws DatabaseException;

	TeacherLoginEntity updateLogin(Long id, TeacherLogin login) throws DatabaseException;

}
