package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionaire.dto.Student;
import com.questionaire.entity.StudentEntity;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.ClassRepository;
import com.questionaire.repository.StudentRepository;
import com.questionaire.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ClassRepository classRepository;

	@Override
	public Long addStudent(Long roomNo, Student student) throws ServiceException, NotFoundException {
		try {
			classRepository.checkClassRoomNo(roomNo);
			return studentRepository.addStudent(roomNo, student);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<StudentEntity> getStudent(Long roomNo) throws ServiceException, NotFoundException {
		List<StudentEntity> student;
		try {
			classRepository.checkClassRoomNo(roomNo);
			student = studentRepository.getStudent(roomNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		return student;
	}

	@Override
	public StudentEntity getStudentById(Long rollNo) throws ServiceException, NotFoundException {

		try {
			studentRepository.checkStudent(rollNo);
			return studentRepository.getStudentById(rollNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public StudentEntity updateStudent(Long roomNo, Long rollNo, Student student)
			throws ServiceException, NotFoundException {
		try {
			classRepository.checkClassRoomNo(roomNo);
			studentRepository.checkStudent(rollNo);
			studentRepository.checkClassStud(roomNo, rollNo);
			return studentRepository.updateStudent(roomNo, rollNo, student);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public String deleteStudent(Long rollNo) throws ServiceException, NotFoundException {
		try {
			studentRepository.checkStudent(rollNo);
			return studentRepository.deleteStudent(rollNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
