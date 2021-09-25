package com.questionaire.repository;

import java.util.List;

import com.questionaire.dto.Subject;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.SubjectNotFoundException;

public interface SubjectRepository {

	void checkSubject(String code) throws SubjectNotFoundException;

	void checkSubjectRoom(Long roomNo, String code) throws SubjectNotFoundException;
	
	String addSubject(Long roomNo, Subject subject) throws DatabaseException;

	List<SubjectEntity> getSubject(Long roomNo) throws DatabaseException;

	SubjectEntity updateSubject(Long roomNo, String subCode, Subject subject) throws DatabaseException;

	String deleteSubject(String subCode) throws DatabaseException;

}
