package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.TeacherLogin;
import com.questionaire.exception.DatabaseException;

public interface TeacherLoginRepository {

	TeacherLogin createLogin(Long id, TeacherLogin login) throws DatabaseException;

	List<TeacherLogin> getDetails(Long id) throws DatabaseException;

	TeacherLogin updateLogin(Long id, TeacherLogin login) throws DatabaseException;

}
