package com.questionaire.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.questionaire.entity.Subject;
import com.questionaire.repository.SubjectRepository;
import com.questionaire.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectRepository subjectRepository;
	@Override
	public ResponseEntity<String> addSubject(Long roomNo,Subject subject) {
		return subjectRepository.addSubject(roomNo,subject);
	}
	public List<Subject> getSubject(Long roomNo)
	{
		List<Subject>subject=subjectRepository.getSubject(roomNo);
		return subject;
	}
	@Override
	public ResponseEntity<String> updateSubject(Long roomNo, String subCode, Subject subject) {
		return subjectRepository.updateSubject(roomNo,subCode,subject);
	}
	@Override
	public ResponseEntity<String> deleteSubject(String subCode) {
		return subjectRepository.deleteSubject(subCode);
	}
}
