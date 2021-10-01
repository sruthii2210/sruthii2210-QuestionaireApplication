package com.questionaire.repository;

import com.questionaire.dto.StudentLogin;
import com.questionaire.entity.StudentLoginEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.IdNotFoundException;

public interface StudentLoginRepository {

	void checkAutoId(Long id) throws IdNotFoundException;

	Long createLogin(Long id, StudentLogin login) throws DatabaseException;

	StudentLoginEntity getDetails(Long id) throws DatabaseException;

	StudentLoginEntity updateLogin(Long id, StudentLogin login) throws DatabaseException;
}
