package com.questionaire.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResultModel {
	private Long rollNo;
	private String code;
	private Long quizId;
	private String name;
	private String subjectName;
	private Integer score;
	
	public ResultModel(Long rollNo, String code, Long quizId) {
		super();
		this.rollNo = rollNo;
		this.code = code;
		this.quizId = quizId;
	}

	public ResultModel(Long rollNo, String code, Long quizId, String name, String subjectName, Integer score) {
		super();
		this.rollNo = rollNo;
		this.code = code;
		this.quizId = quizId;
		this.name = name;
		this.subjectName = subjectName;
		this.score=score;
	}
	
	
	
	

}
