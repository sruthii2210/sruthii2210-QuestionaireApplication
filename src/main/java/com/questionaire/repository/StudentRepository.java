package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.Student;
import com.questionaire.exception.DatabaseException;

public interface StudentRepository {

	ResponseEntity<String> addStudent(Long roomNo,Student student);
	List<Student> getStudent(Long roomNo) throws DatabaseException;
	List<Student> getStudentById(Long rollNo);
	ResponseEntity<String> updateStudent(Long roomNo,Long rollNo,Student student);
	ResponseEntity<String> deleteStudent(Long rollNo);
	
	
}
