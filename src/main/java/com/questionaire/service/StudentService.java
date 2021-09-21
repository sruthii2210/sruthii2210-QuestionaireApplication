package com.questionaire.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.Student;
import com.questionaire.exception.ServiceException;

public interface StudentService {

	Student addStudent(Long roomNo, Student student) throws ServiceException;

	List<Student> getStudent(Long roomNo) throws ServiceException;

	Student getStudentById(Long rollNo) throws ServiceException;

	Student updateStudent(Long roomNo, Long rollNo, Student student) throws ServiceException;

	String deleteStudent(Long rollNo) throws ServiceException;

}
