package com.questionaire.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherSubjectModel {

	private Long teacherId;
	private String subCode;
	public TeacherSubjectModel(Long teacherId, String subCode) {
		super();
		this.teacherId = teacherId;
		this.subCode = subCode;
	}
	
	
	
}
