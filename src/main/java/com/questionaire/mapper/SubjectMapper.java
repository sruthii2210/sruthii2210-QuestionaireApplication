package com.questionaire.mapper;

import com.questionaire.dto.Subject;
import com.questionaire.entity.ClassRoom;
import com.questionaire.entity.SubjectEntity;

public class SubjectMapper {

	public static SubjectEntity mapSubject(Subject subject,Long roomNo) {
		
		ClassRoom classRoom=new ClassRoom();
		classRoom.setRoomNo(roomNo);
		SubjectEntity subjectEntity=new SubjectEntity();
		subjectEntity.setClassRoom(classRoom);
		subjectEntity.setSubCode(subject.getSubCode());
		subjectEntity.setSubName(subject.getSubName());
	
			return subjectEntity;
}
}
