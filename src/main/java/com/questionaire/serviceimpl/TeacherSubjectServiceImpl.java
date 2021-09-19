package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionaire.entity.TeacherSubject;
import com.questionaire.entity.TeacherSubjectModel;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.repository.TeacherSubjectRepository;
import com.questionaire.service.TeacherSubjectService;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService{
	@Autowired
	private TeacherSubjectRepository teacherSubjectRepositoryImpl;
	@Override
	public ResponseEntity<String> assignTeacherSubject(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws TeacherNotFoundException, SubjectNotFoundException {
		// TODO Auto-generated method stub
		return teacherSubjectRepositoryImpl.assignTeacherSubject(teacherId,subjectCode,teacherSubjectDetails);
	}
	@Override
	public ResponseEntity<String> updateTeacherSubjectAssign(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws TeacherNotFoundException, SubjectNotFoundException {
		// TODO Auto-generated method stub
		return teacherSubjectRepositoryImpl.updateTeacherSubjectAssign(teacherId,subjectCode,teacherSubjectDetails);
	}
	@Override
	public ResponseEntity<String> deleteTeacherSubjectAssign(Long teacherId, String subjectCode) throws TeacherNotFoundException, SubjectNotFoundException {
		// TODO Auto-generated method stub
		return teacherSubjectRepositoryImpl.deleteTeacherSubjectAssign(teacherId,subjectCode);
	}
	@Override
	public List<TeacherSubjectModel> getSubject(Long id) throws TeacherNotFoundException {
		// TODO Auto-generated method stub
		return teacherSubjectRepositoryImpl.getSubject(id);
	}

}
