package com.questionaire.mapper;

import com.questionaire.dto.Answer;
import com.questionaire.entity.AnswerEntity;
import com.questionaire.entity.QuestionEntity;

public class AnswerMapper {

	public static AnswerEntity mapAnswer(Integer quesNo,Answer answer)
	{
		QuestionEntity question=new QuestionEntity();
		question.setQuesNo(quesNo);
		AnswerEntity entity=new AnswerEntity();
		entity.setQues(question);
		entity.setOption1(answer.getOption1());
		entity.setOption2(answer.getOption2());
		entity.setOption3(answer.getOption3());
		entity.setOption4(answer.getOption4());
		entity.setCrctAns(answer.getCrctAns());
		return entity;
	
	}
}
