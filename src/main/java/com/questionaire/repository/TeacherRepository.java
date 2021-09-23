package com.questionaire.repository;

import java.util.List;

import com.questionaire.dto.Teacher;
import com.questionaire.entity.TeacherEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.TeacherNotFoundException;

public interface TeacherRepository {

	void checkTeacher(Long id) throws TeacherNotFoundException;

	Long addTeacherDetails(Teacher teacherDetails) throws DatabaseException;

	List<TeacherEntity> getAllTeacherDetails() throws DatabaseException;

	TeacherEntity updateTeacherDetails(Long id, Teacher teacherDetails) throws DatabaseException;

	String deleteTeacherDetails(Long id) throws DatabaseException;

	TeacherEntity getParticularTeacherDetails(Long id) throws DatabaseException;
}
