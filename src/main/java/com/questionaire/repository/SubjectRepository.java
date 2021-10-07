package com.questionaire.repository;

import java.util.List;

import com.questionaire.dto.Subject;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.SubjectNotFoundException;

public interface SubjectRepository {

	void checkSubject(String code) throws SubjectNotFoundException;

	// void checkSubjectRoom(String standard, String code) throws
	// SubjectNotFoundException;

	String addSubject(String standard, Subject subject) throws DatabaseException;

	List<SubjectEntity> getSubject(String standard) throws DatabaseException;

	SubjectEntity updateSubject(String standard, String subCode, Subject subject) throws DatabaseException;

	String deleteSubject(String subCode) throws DatabaseException;

	List<Long> getAllTeachers(Long roomNo,List<String> subjectCodes) throws DatabaseException;

}
