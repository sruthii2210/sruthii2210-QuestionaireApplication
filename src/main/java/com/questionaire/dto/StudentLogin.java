package com.questionaire.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentLogin {

	private Long autoId;
	private Student rollNo;;
	private String password;
}
