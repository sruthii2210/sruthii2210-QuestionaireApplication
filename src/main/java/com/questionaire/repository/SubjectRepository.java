package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.Subject;
import com.questionaire.exception.DatabaseException;

public interface SubjectRepository {

	Subject addSubject(Long roomNo,Subject subject) throws DatabaseException;
	List<Subject> getSubject(Long roomNo) throws DatabaseException;
	Subject updateSubject(Long roomNo,String subCode,Subject subject) throws DatabaseException;
	String deleteSubject(String subCode) throws DatabaseException;
	
	
}
