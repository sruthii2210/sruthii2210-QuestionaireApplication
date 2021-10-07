package com.questionaire.mapper;

import com.questionaire.dto.Quiz;
import com.questionaire.entity.QuizEntity;
import com.questionaire.entity.SubjectEntity;
import com.questionaire.entity.TeacherEntity;

public class QuizMapper {

	public static QuizEntity mapQuiz(Long id, String subCode, Quiz quiz) {

		SubjectEntity subject = new SubjectEntity();
		subject.setCode(subCode);
		TeacherEntity teacher = new TeacherEntity();
		teacher.setId(id);
		QuizEntity quizEntity = new QuizEntity();
		quizEntity.setSubject(subject);
		quizEntity.setTeacher(teacher);
		quizEntity.setName(quiz.getName());
		quizEntity.setPassPercent(quiz.getPassPercent());
		quizEntity.setQuizDate(quiz.getQuizDate());
		return quizEntity;

	}
}
