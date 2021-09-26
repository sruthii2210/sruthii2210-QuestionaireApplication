package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionaire.dto.TeacherSubject;
import com.questionaire.entity.TeacherSubjectEntity;
import com.questionaire.entity.TeacherSubjectModel;
import com.questionaire.exception.DatabaseException;
import com.questionaire.exception.NotFoundException;
import com.questionaire.exception.ServiceException;
import com.questionaire.repository.TeacherSubjectRepository;
import com.questionaire.repositoryimpl.ClassRepositoryImpl;
import com.questionaire.repositoryimpl.SubjectRepositoryImpl;
import com.questionaire.repositoryimpl.TeacherRepositoryImpl;
import com.questionaire.service.TeacherSubjectService;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService {
	@Autowired
	private TeacherSubjectRepository teacherSubjectRepositoryImpl;
	
	@Autowired
	private ClassRepositoryImpl classRepository;
	@Autowired
	private TeacherRepositoryImpl teacherRepository;
	@Autowired
	private SubjectRepositoryImpl subjectRepository;

	@Override
	public Long assignTeacherSubject(Long teacherId, String subjectCode,Long roomNo, TeacherSubject teacherSubjectDetails)
			throws ServiceException, NotFoundException {
		try {
			teacherRepository.checkTeacher(teacherId);
			subjectRepository.checkSubject(subjectCode);
			return teacherSubjectRepositoryImpl.assignTeacherSubject(teacherId, subjectCode,roomNo, teacherSubjectDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public TeacherSubjectEntity updateTeacherSubjectAssign(Long teacherId, String subjectCode,
			Long roomNo,TeacherSubject teacherSubjectDetails) throws ServiceException, NotFoundException {
		try {
			teacherRepository.checkTeacher(teacherId);
			subjectRepository.checkSubject(subjectCode);
			return teacherSubjectRepositoryImpl.updateTeacherSubjectAssign(teacherId, subjectCode,roomNo,
					teacherSubjectDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public String deleteTeacherSubjectAssign(Long teacherId, String subjectCode) throws ServiceException, NotFoundException {

		try {
			teacherRepository.checkTeacher(teacherId);
			subjectRepository.checkSubject(subjectCode);
			return teacherSubjectRepositoryImpl.deleteTeacherSubjectAssign(teacherId, subjectCode);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<TeacherSubjectModel> getSubject(Long id) throws ServiceException, NotFoundException {

		try {
			teacherRepository.checkTeacher(id);
			return teacherSubjectRepositoryImpl.getSubject(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public TeacherSubjectModel getQuiz(Long roomNo, String code) throws NotFoundException, ServiceException {
		try {
			classRepository.checkClassRoomNo(roomNo);
			subjectRepository.checkSubject(code);
			return teacherSubjectRepositoryImpl.getQuiz(roomNo,code);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
