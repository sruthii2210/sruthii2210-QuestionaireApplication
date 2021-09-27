package com.questionaire.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {

	private Long autoId;
	private Student student;
	private Subject subject;
	private Quiz quiz;
	private Integer score;
}
