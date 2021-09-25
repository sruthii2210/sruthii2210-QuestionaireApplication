package com.questionaire.mapper;

import com.questionaire.dto.Result;
import com.questionaire.entity.QuizEntity;
import com.questionaire.entity.ResultEntity;
import com.questionaire.entity.StudentEntity;
import com.questionaire.entity.SubjectEntity;

public class ResultMapper {

	public static ResultEntity mapResult(Long rollNo, String subCode, Long id,  Result result) {
		StudentEntity student = new StudentEntity();
		SubjectEntity subject = new SubjectEntity();
		QuizEntity quiz = new QuizEntity();

		student.setRollNo(rollNo);
		subject.setSubCode(subCode);
		quiz.setAutoId(id);
		ResultEntity entity = new ResultEntity();
		
		entity.setStud(student);
		entity.setSub(subject);
		entity.setQuiz(quiz);
		entity.setScore(result.getScore());
		
		return entity;

	}
}
