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
		subject.setCode(subCode);
		quiz.setAutoId(id);
		ResultEntity resultEntity = new ResultEntity();
		
		resultEntity.setStudent(student);
		resultEntity.setSubject(subject);
		resultEntity.setQuiz(quiz);
		resultEntity.setScore(result.getScore());
		
		return resultEntity;

	}
}
