package com.questionaire.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherSubject {

	private Long autoId;
	private Teacher teacher;
	private Subject subject;
	private ClassDetails classDetails;
}
