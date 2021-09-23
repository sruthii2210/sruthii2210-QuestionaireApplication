package com.questionaire.mapper;

import com.questionaire.dto.Teacher;
import com.questionaire.entity.TeacherEntity;

public class TeacherMapper {

	public static TeacherEntity mapTeacher(Teacher teacher) {

		TeacherEntity entity = new TeacherEntity();
		entity.setId(teacher.getId());
		entity.setFirstName(teacher.getFirstName());
		entity.setLastName(teacher.getLastName());
		entity.setDateOfBirth(teacher.getDateOfBirth());
		entity.setGender(teacher.getGender());
		entity.setQualification(teacher.getQualification());
		entity.setEmail(teacher.getEmail());
		entity.setContactNo(teacher.getContactNo());
		entity.setAddress(teacher.getAddress());

		return entity;
	}
}
