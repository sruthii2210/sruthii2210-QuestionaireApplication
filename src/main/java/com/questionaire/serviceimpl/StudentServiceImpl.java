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
	public ResponseEntity<String> addStudent(Long roomNo,Student student) {
		return studentRepository.addStudent(roomNo,student);
	}
	@Override
	public List<Student> getStudent(Long roomNo) throws ServiceException {
		List<Student> student;
		try {
			student = studentRepository.getStudent(roomNo);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
		return student;
	}
	@Override
	public List<Student> getStudentById(Long rollNo) {
		List<Student> student=studentRepository.getStudentById(rollNo);
		return student;
	}
	@Override
	public ResponseEntity<String> updateStudent(Long roomNo,Long rollNo, Student student) {
		return studentRepository.updateStudent(roomNo,rollNo,student);
	}
	@Override
	public ResponseEntity<String> deleteStudent(Long rollNo) {
		return studentRepository.deleteStudent(rollNo);
	}

}
