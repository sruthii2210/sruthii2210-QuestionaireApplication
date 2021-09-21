package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.questionaire.entity.Student;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.StudentRepository;
import com.questionaire.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	@Override
	public Student addStudent(Long roomNo,Student student) throws ServiceException {
		try {
			return studentRepository.addStudent(roomNo,student);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public List<Student> getStudent(Long roomNo) throws ServiceException {
		List<Student> student;
		try {
			student = studentRepository.getStudent(roomNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		return student;
	}
	@Override
	public Student getStudentById(Long rollNo) throws ServiceException {
		
		try {
			return studentRepository.getStudentById(rollNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public Student updateStudent(Long roomNo,Long rollNo, Student student) throws ServiceException {
		try {
			return studentRepository.updateStudent(roomNo,rollNo,student);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public String deleteStudent(Long rollNo) throws ServiceException {
		try {
			return studentRepository.deleteStudent(rollNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
