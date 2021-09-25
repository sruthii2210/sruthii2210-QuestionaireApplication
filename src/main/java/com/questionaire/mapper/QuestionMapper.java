package com.questionaire.mapper;

import com.questionaire.dto.Question;
import com.questionaire.entity.QuestionEntity;
import com.questionaire.entity.QuizEntity;

public class QuestionMapper {

	public static QuestionEntity mapQuestion(Long id,Question question)
	{
		QuizEntity quiz=new QuizEntity();
		quiz.setAutoId(id);
		QuestionEntity entity=new QuestionEntity();
		entity.setQuiz(quiz);
		entity.setQuestion(question.getQuestion());
		return entity;
	}
}
