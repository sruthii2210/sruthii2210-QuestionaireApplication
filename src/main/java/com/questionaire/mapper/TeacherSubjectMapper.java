package com.questionaire.mapper;

import com.questionaire.dto.TeacherSubject;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.entity.TeacherEntity;
import com.questionaire.entity.TeacherSubjectEntity;

public class TeacherSubjectMapper {

	public static TeacherSubjectEntity mapTeacherSubject(Long teacherId, String subjectCode,TeacherSubject teacherSubject)
	{
		TeacherEntity teacherDetails = new TeacherEntity();
		teacherDetails.setId(teacherId);
		SubjectEntity subjectDetails = new SubjectEntity();
		subjectDetails.setSubCode(subjectCode);
		TeacherSubjectEntity teacherSubjectAssignDetails = new TeacherSubjectEntity();
		teacherSubjectAssignDetails.setTeacher(teacherDetails);
		teacherSubjectAssignDetails.setSubject(subjectDetails);
		
		return teacherSubjectAssignDetails;
	}
}
