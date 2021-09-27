package com.questionaire.service;

import java.util.List;

import com.questionaire.dto.TeacherSubject;
import com.questionaire.entity.TeacherSubjectEntity;
import com.questionaire.entity.TeacherSubjectModel;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;

public interface TeacherSubjectService {
	Long assignTeacherSubject(Long id, String subCode, Long roomNo, TeacherSubject teacherSubjectDetails)
			throws ServiceException, NotFoundException;

	TeacherSubjectEntity updateTeacherSubjectAssign(Long id, String subCode, Long roomNo,
			TeacherSubject teacherSubjectDetails) throws ServiceException, NotFoundException;

	String deleteTeacherSubjectAssign(Long id, String subCode) throws ServiceException, NotFoundException;

	List<TeacherSubjectModel> getSubject(Long id) throws ServiceException, NotFoundException;

	TeacherSubjectModel getQuiz(Long roomNo, String code) throws NotFoundException, ServiceException;
}
