package com.questionaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionaire.entity.TeacherSubject;
import com.questionaire.entity.TeacherSubjectModel;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.service.TeacherSubjectService;



@RestController
@RequestMapping("/teacherSubject")
@CrossOrigin("http://localhost:4200")
public class TeacherSubjectController {
	@Autowired
	private TeacherSubjectService teacherSubjectServiceImpl;
	@PostMapping("/teacher/{id}/subject/{subCode}/assignTeacherSubject")
	public ResponseEntity<String> assignTeacherSubject(@PathVariable("id") Long teacherId,@PathVariable("subCode") String subCode,@RequestBody TeacherSubject teacherSubjectDetails) throws TeacherNotFoundException, SubjectNotFoundException
	{
		return teacherSubjectServiceImpl.assignTeacherSubject(teacherId,subCode,teacherSubjectDetails);
	}
	@PutMapping("/teacher/{id}/subject/{subCode}/updateTeacherSubjectAssign")
	public ResponseEntity<String> updateTeacherSubjectAssign(@PathVariable("id") Long teacherId,@PathVariable("subCode") String subCode,@RequestBody TeacherSubject teacherSubjectDetails) throws TeacherNotFoundException, SubjectNotFoundException
	{
		return teacherSubjectServiceImpl.updateTeacherSubjectAssign(teacherId,subCode,teacherSubjectDetails);
	}
	@DeleteMapping("/teacher/{id}/subject/{subCode}/deleteTeacherSubjectAssign")
	public ResponseEntity<String> deleteTeacherSubjectAssign(@PathVariable("id") Long teacherId,@PathVariable("subCode") String subCode) throws TeacherNotFoundException, SubjectNotFoundException
	{
		return teacherSubjectServiceImpl.deleteTeacherSubjectAssign(teacherId,subCode);
	}
	@GetMapping("/teacher/{id}/getSubject")
	public List<TeacherSubjectModel> getSubject(@PathVariable("id") Long teacherId) throws TeacherNotFoundException
	{
		return teacherSubjectServiceImpl.getSubject(teacherId);
	}
}
