package com.questionaire.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.questionaire.entity.TeacherSubject;
import com.questionaire.entity.TeacherSubjectModel;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.exception.TeacherNotFoundException;

public interface TeacherSubjectService {
	ResponseEntity<String> assignTeacherSubject(Long id,String subCode,TeacherSubject teacherSubjectDetails) throws TeacherNotFoundException, SubjectNotFoundException;
	ResponseEntity<String> updateTeacherSubjectAssign(Long id,String subCode,TeacherSubject teacherSubjectDetails) throws TeacherNotFoundException, SubjectNotFoundException;
	ResponseEntity<String> deleteTeacherSubjectAssign(Long id,String subCode) throws TeacherNotFoundException, SubjectNotFoundException;
	List<TeacherSubjectModel> getSubject(Long id) throws TeacherNotFoundException;
}
