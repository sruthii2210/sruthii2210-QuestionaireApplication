package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.questionaire.entity.Teacher;
import com.questionaire.exception.TeacherNotFoundException;

public interface TeacherService {
	ResponseEntity<String> addTeacherDetails(Teacher teacherDetails);
	ResponseEntity<List<Teacher>> getAllTeacherDetails();
	ResponseEntity<String> updateTeacherDetails(Long id,Teacher teacherDetails) throws TeacherNotFoundException;
	ResponseEntity<String> deleteTeacherDetails(Long id);
	ResponseEntity<Teacher> getParticularTeacherDetails(Long id);
}
