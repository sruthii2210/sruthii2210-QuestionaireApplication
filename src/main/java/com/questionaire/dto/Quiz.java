package com.questionaire.dto;

import java.util.Date;

import javax.validation.constraints.Max;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Quiz {

	private Long autoId;

	private String name;

	private String subCode;

	private Long id;
	private Date quizDate;
	@Max(100)
	private int passPercent;
	private String status;
}
