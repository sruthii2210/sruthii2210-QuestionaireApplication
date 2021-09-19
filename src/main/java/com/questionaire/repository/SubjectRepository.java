package com.questionaire.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.questionaire.entity.Subject;

public interface SubjectRepository {

	ResponseEntity<String> addSubject(Long roomNo,Subject subject);
	List<Subject> getSubject(Long roomNo);
	ResponseEntity<String>updateSubject(Long roomNo,String subCode,Subject subject);
	ResponseEntity<String>deleteSubject(String subCode);
	
	
}
