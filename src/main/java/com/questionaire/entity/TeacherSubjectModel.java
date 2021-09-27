package com.questionaire.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TeacherSubjectModel {

	private Long roomNo;
	private Long teacherId;
	private String code;

	public TeacherSubjectModel(Long roomNo, Long teacherId, String code) {
		super();
		this.roomNo = roomNo;
		this.teacherId = teacherId;
		this.code = code;
	}

	public TeacherSubjectModel(Long roomNo, String code) {
		super();
		this.roomNo = roomNo;
		this.code = code;
	}

}
