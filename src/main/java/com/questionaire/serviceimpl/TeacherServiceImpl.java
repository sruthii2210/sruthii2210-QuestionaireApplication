package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionaire.entity.Teacher;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.repository.TeacherRepository;
import com.questionaire.service.TeacherService;



@Service
public class TeacherServiceImpl implements TeacherService{
	@Autowired
	private TeacherRepository teacherRepositoryImpl;
	@Override
	public ResponseEntity<String> addTeacherDetails(Teacher teacherDetails) {
		// TODO Auto-generated method stub
		return teacherRepositoryImpl.addTeacherDetails(teacherDetails);
	}
	@Override
	public ResponseEntity<List<Teacher>> getAllTeacherDetails() {
		// TODO Auto-generated method stub
		return teacherRepositoryImpl.getAllTeacherDetails();
	}
	@Override
	public ResponseEntity<String> updateTeacherDetails(Long id, Teacher teacherDetails) throws TeacherNotFoundException {
		// TODO Auto-generated method stub
		return teacherRepositoryImpl.updateTeacherDetails(id,teacherDetails);
	}
	@Override
	public ResponseEntity<String> deleteTeacherDetails(Long id) {
		// TODO Auto-generated method stub
		return teacherRepositoryImpl.deleteTeacherDetails(id);
	}
	@Override
	public ResponseEntity<Teacher> getParticularTeacherDetails(Long id) {
		// TODO Auto-generated method stub
		return teacherRepositoryImpl.getParticularTeacherDetails(id);
	}
	
}
