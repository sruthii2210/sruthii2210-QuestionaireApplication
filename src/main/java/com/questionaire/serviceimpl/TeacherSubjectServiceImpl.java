package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionaire.entity.TeacherSubject;
import com.questionaire.entity.TeacherSubjectModel;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.ServiceException;
import com.questionaire.exception.SubjectNotFoundException;
import com.questionaire.exception.TeacherNotFoundException;
import com.questionaire.repository.TeacherSubjectRepository;
import com.questionaire.service.TeacherSubjectService;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService {
	@Autowired
	private TeacherSubjectRepository teacherSubjectRepositoryImpl;

	@Override
	public TeacherSubject assignTeacherSubject(Long teacherId, String subjectCode, TeacherSubject teacherSubjectDetails)
			throws ServiceException {
		try {
			return teacherSubjectRepositoryImpl.assignTeacherSubject(teacherId, subjectCode, teacherSubjectDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public TeacherSubject updateTeacherSubjectAssign(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws ServiceException {
		try {
			return teacherSubjectRepositoryImpl.updateTeacherSubjectAssign(teacherId, subjectCode,
					teacherSubjectDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public String deleteTeacherSubjectAssign(Long teacherId, String subjectCode) throws ServiceException {

		try {
			return teacherSubjectRepositoryImpl.deleteTeacherSubjectAssign(teacherId, subjectCode);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<TeacherSubjectModel> getSubject(Long id) throws ServiceException {

		try {
			return teacherSubjectRepositoryImpl.getSubject(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
