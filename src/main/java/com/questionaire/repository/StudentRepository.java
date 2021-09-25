package com.questionaire.repository;

import java.util.List;
import com.questionaire.dto.Student;
import com.questionaire.entity.StudentEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.StudentIdNotFoundException;

public interface StudentRepository {

	void checkStudent(Long rollNo) throws StudentIdNotFoundException;

	void checkClassStud(Long roomNo, Long rollNo) throws StudentIdNotFoundException;

	Long addStudent(Long roomNo, Student student) throws DatabaseException;

	List<StudentEntity> getStudent(Long roomNo) throws DatabaseException;

	StudentEntity getStudentById(Long rollNo) throws DatabaseException;

	StudentEntity updateStudent(Long roomNo, Long rollNo, Student student) throws DatabaseException;

	String deleteStudent(Long rollNo) throws DatabaseException;

}
