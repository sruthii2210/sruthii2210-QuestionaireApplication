package com.questionaire.mapper;

import com.questionaire.dto.TeacherLogin;
import com.questionaire.entity.TeacherEntity;
import com.questionaire.entity.TeacherLoginEntity;

public class TeacherLoginMapper {

	public static TeacherLoginEntity mapTeacherLogin(Long id, TeacherLogin teacher) {
		TeacherEntity teacherEntity = new TeacherEntity();
		teacherEntity.setId(id);
		TeacherLoginEntity teacherLogin = new TeacherLoginEntity();
		teacherLogin.setTeacherId(teacherEntity);
		teacherLogin.setPassword(teacher.getPassword());
		return teacherLogin;
	}
}
