package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.questionaire.entity.ClassRoom;
import com.questionaire.exception.DatabaseException;

public interface ClassRepository {

	ClassRoom addClass(ClassRoom classDetails) throws DatabaseException;
	List<ClassRoom> getClassDetails() throws DatabaseException;
	ClassRoom updateClass(Long roomNo,ClassRoom classDetails) throws DatabaseException;
	ClassRoom getClass(String standard,String section) throws DatabaseException;
	
	
}
