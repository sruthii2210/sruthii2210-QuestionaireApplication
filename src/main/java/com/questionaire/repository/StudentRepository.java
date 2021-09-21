package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.Student;
import com.questionaire.exception.DatabaseException;

public interface StudentRepository {

	Student addStudent(Long roomNo, Student student) throws DatabaseException;

	List<Student> getStudent(Long roomNo) throws DatabaseException;

	Student getStudentById(Long rollNo) throws DatabaseException;

	Student updateStudent(Long roomNo, Long rollNo, Student student) throws DatabaseException;

	String deleteStudent(Long rollNo) throws DatabaseException;

}
