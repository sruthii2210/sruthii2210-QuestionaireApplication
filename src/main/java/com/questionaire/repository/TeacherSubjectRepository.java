package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.questionaire.entity.TeacherSubject;
import com.questionaire.entity.TeacherSubjectModel;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.exception.TeacherNotFoundException;


public interface TeacherSubjectRepository {
	ResponseEntity<String> assignTeacherSubject(Long teacherId,String subjectCode,TeacherSubject teacherSubjectDetails) throws TeacherNotFoundException, SubjectNotFoundException;
	ResponseEntity<String> updateTeacherSubjectAssign(Long teacherId,String subjectCode,TeacherSubject teacherSubjectDetails) throws TeacherNotFoundException, SubjectNotFoundException;
	ResponseEntity<String> deleteTeacherSubjectAssign(Long teacherId,String subjectCode) throws TeacherNotFoundException, SubjectNotFoundException;
	 List<TeacherSubjectModel> getSubject(Long teacherId) throws TeacherNotFoundException;
	
}
