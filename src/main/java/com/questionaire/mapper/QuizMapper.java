package com.questionaire.mapper;

import com.questionaire.dto.Quiz;
import com.questionaire.entity.QuizEntity;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.entity.TeacherEntity;

public class QuizMapper {

	public static QuizEntity mapQuiz(Long id,String subCode,Quiz quiz) {

		SubjectEntity subject = new SubjectEntity();
		subject.setSubCode(subCode);
		TeacherEntity teacher=new TeacherEntity();
		teacher.setId(id);
		QuizEntity entity=new QuizEntity();
		entity.setSubject(subject);
		entity.setTeacher(teacher);
		entity.setName(quiz.getName());
		return entity;
		

	}
}
