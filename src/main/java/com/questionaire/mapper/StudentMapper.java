package com.questionaire.mapper;

import com.questionaire.dto.Student;
import com.questionaire.entity.ClassRoom;
import com.questionaire.entity.StudentEntity;

public class StudentMapper {

	public static StudentEntity mapStudent(Long roomNo,Student student)
	{
		ClassRoom classRoom=new ClassRoom();
		StudentEntity entity=new StudentEntity();
		classRoom.setRoomNo(roomNo);
		entity.setClassRoom(classRoom);
		entity.setRollNo(student.getRollNo());
		entity.setName(student.getName());
		entity.setDateOfBirth(student.getDateOfBirth());
		entity.setGender(student.getGender());
		entity.setAddress(student.getAddress());
		return entity;
	}
}
