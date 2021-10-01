package com.questionaire.repository;

import java.util.List;

import com.questionaire.dto.TeacherSubject;
import com.questionaire.entity.TeacherSubjectEntity;
import com.questionaire.entity.TeacherSubjectModel;
import com.questionaire.exception.DatabaseException;

public interface TeacherSubjectRepository {
	Long assignTeacherSubject(Long teacherId, String subjectCode, Long roomNo, TeacherSubject teacherSubjectDetails)
			throws DatabaseException;

	TeacherSubjectEntity updateTeacherSubjectAssign(Long teacherId, String subjectCode, Long roomNo,
			TeacherSubject teacherSubjectDetails) throws DatabaseException;

	String deleteTeacherSubjectAssign(Long teacherId, String subjectCode) throws DatabaseException;

	List<TeacherSubjectModel> getSubject(Long teacherId) throws DatabaseException;

	TeacherSubjectModel getQuiz(Long roomNo, String code) throws DatabaseException;

	List<TeacherSubjectModel> getRoomNo(Long id, String code) throws DatabaseException;

}
