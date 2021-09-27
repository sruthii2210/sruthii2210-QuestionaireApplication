package com.questionaire.mapper;

import com.questionaire.dto.ClassDetails;
import com.questionaire.entity.ClassRoom;

public class ClassMapper {

	public static ClassRoom mapClassRoom(ClassDetails classDetails) {

		ClassRoom classRoom = new ClassRoom();
		classRoom.setRoomNo(classDetails.getRoomNo());
		classRoom.setSection(classDetails.getSection());
		classRoom.setStandard(classDetails.getStandard());

		return classRoom;
	}
}
