package com.questionaire.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherLogin {
	private Long autoId;
	private Teacher userid;
	private String password;
}
