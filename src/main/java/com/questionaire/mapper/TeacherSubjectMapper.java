package com.questionaire.mapper;

import com.questionaire.dto.TeacherSubject;
import com.questionaire.entity.ClassRoom;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.entity.TeacherEntity;
import com.questionaire.entity.TeacherSubjectEntity;

public class TeacherSubjectMapper {

	public static TeacherSubjectEntity mapTeacherSubject(Long teacherId, String subjectCode, Long roomNo,
			TeacherSubject teacherSubject) {
		ClassRoom classRoom = new ClassRoom();
		classRoom.setRoomNo(roomNo);
		TeacherEntity teacherDetails = new TeacherEntity();
		teacherDetails.setId(teacherId);
		SubjectEntity subjectDetails = new SubjectEntity();
		subjectDetails.setCode(subjectCode);
		TeacherSubjectEntity teacherSubjectAssignDetails = new TeacherSubjectEntity();
		teacherSubjectAssignDetails.setTeacher(teacherDetails);
		teacherSubjectAssignDetails.setSubject(subjectDetails);
		teacherSubjectAssignDetails.setClassRoom(classRoom);

		return teacherSubjectAssignDetails;
	}
}
