package com.questionaire.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class Question {

	private Integer quesNo;
	private String question;
	private Quiz quiz;
}
