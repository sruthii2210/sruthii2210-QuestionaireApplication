package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.questionaire.entity.TeacherSubject;
import com.questionaire.entity.TeacherSubjectModel;
import com.questionaire.exception.ServiceException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.exception.TeacherNotFoundException;

public interface TeacherSubjectService {
	TeacherSubject assignTeacherSubject(Long id, String subCode, TeacherSubject teacherSubjectDetails)
			throws ServiceException;

	TeacherSubject updateTeacherSubjectAssign(Long id, String subCode, TeacherSubject teacherSubjectDetails)
			throws ServiceException;

	String deleteTeacherSubjectAssign(Long id, String subCode) throws ServiceException;

	List<TeacherSubjectModel> getSubject(Long id) throws ServiceException;
}
