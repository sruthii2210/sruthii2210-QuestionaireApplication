package com.questionaire.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Answer {

	private Long autoId;
	private Long ques;
	@NotNull
	private String option1;
	@NotNull
	private String option2;
	@NotNull
	private String option3;
	@NotNull
	private String option4;
	private Integer crctAns;
	private Question question;
}
