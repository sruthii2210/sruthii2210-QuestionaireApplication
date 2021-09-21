package com.questionaire.repository;

import java.util.List;
import com.questionaire.entity.TeacherSubject;
import com.questionaire.entity.TeacherSubjectModel;
import com.questionaire.exception.DatabaseException;


public interface TeacherSubjectRepository {
	TeacherSubject assignTeacherSubject(Long teacherId, String subjectCode, TeacherSubject teacherSubjectDetails)
			throws DatabaseException;

	TeacherSubject updateTeacherSubjectAssign(Long teacherId, String subjectCode, TeacherSubject teacherSubjectDetails)
			throws DatabaseException;

	String deleteTeacherSubjectAssign(Long teacherId, String subjectCode) throws DatabaseException;

	List<TeacherSubjectModel> getSubject(Long teacherId) throws DatabaseException;

}
