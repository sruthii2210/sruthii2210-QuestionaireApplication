package com.questionaire.mapper;

import com.questionaire.dto.TeacherLogin;
import com.questionaire.entity.TeacherEntity;
import com.questionaire.entity.TeacherLoginEntity;

public class TeacherLoginMapper {

	public static TeacherLoginEntity mapTeacherLogin(Long id, TeacherLogin teacher) {
		TeacherEntity teach = new TeacherEntity();
		teach.setId(id);
		TeacherLoginEntity teacherLogin = new TeacherLoginEntity();
		teacherLogin.setUserid(teach);
		teacherLogin.setPassword(teacher.getPassword());
		return teacherLogin;
	}
}
