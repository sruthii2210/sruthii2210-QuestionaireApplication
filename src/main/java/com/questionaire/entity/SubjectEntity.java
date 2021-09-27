package com.questionaire.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class SubjectEntity implements Serializable {

	@Id
	private String code;
	@Column(nullable = false)
	private String name;

//	@ManyToOne(targetEntity = ClassRoom.class, fetch = FetchType.LAZY)
//	@JoinColumn(name = "roomNo", nullable = false)
//	@JsonIgnore
//	private ClassRoom classRoom;

	@Column(nullable = false)
	private String standard;

	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<QuizEntity> quiz;

	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<ResultEntity> results;

	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<TeacherSubjectEntity> teacherSubject;

}
