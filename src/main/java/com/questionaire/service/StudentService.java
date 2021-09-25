package com.questionaire.service;

import java.util.List;
import com.questionaire.dto.Student;
import com.questionaire.entity.StudentEntity;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;


public interface StudentService {

	Long addStudent(Long roomNo, Student student) throws ServiceException, NotFoundException;

	List<StudentEntity> getStudent(Long roomNo) throws ServiceException, NotFoundException;

	StudentEntity getStudentById(Long rollNo) throws ServiceException, NotFoundException;

	StudentEntity updateStudent(Long roomNo, Long rollNo, Student student) throws ServiceException, NotFoundException;

	String deleteStudent(Long rollNo) throws ServiceException, NotFoundException;

}
