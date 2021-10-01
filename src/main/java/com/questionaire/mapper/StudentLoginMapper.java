package com.questionaire.mapper;

import com.questionaire.dto.StudentLogin;
import com.questionaire.entity.StudentEntity;
import com.questionaire.entity.StudentLoginEntity;

public class StudentLoginMapper {
	

	public static StudentLoginEntity mapStudentLogin(Long id, StudentLogin student) {
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setRollNo(id);
		StudentLoginEntity studentLogin = new StudentLoginEntity();
		studentLogin.setStudent(studentEntity);
		studentLogin.setPassword(student.getPassword());
		return studentLogin;
	}
}

