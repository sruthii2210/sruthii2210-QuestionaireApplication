package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.questionaire.entity.Teacher;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.TeacherNotFoundException;

public interface TeacherRepository {
	Teacher addTeacherDetails(Teacher teacherDeteails) throws DatabaseException;

	List<Teacher> getAllTeacherDetails() throws DatabaseException;

	Teacher updateTeacherDetails(Long id, Teacher teacherDetails) throws  DatabaseException;

	ResponseEntity<String> deleteTeacherDetails(Long id);

	Teacher getParticularTeacherDetails(Long id) throws DatabaseException;
}
