package com.questionaire.repository;

import java.util.List;

import com.questionaire.dto.ClassDetails;
import com.questionaire.entity.ClassRoom;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.RoomNoNotFoundException;
import com.questionaire.exception.StandardNotFoundException;

public interface ClassRepository {

	void checkClassRoomNo(Long roomNo) throws RoomNoNotFoundException;

	void checkStandard(String standard) throws StandardNotFoundException;

	Long addClass(ClassDetails classDetails) throws DatabaseException;

	List<ClassRoom> getClassDetails() throws DatabaseException;

	ClassRoom updateClass(Long roomNo, ClassDetails classDetails) throws DatabaseException;

	ClassRoom getClass(String standard, String section) throws DatabaseException;

	ClassRoom getClassDetails(Long roomNo) throws DatabaseException;

}
