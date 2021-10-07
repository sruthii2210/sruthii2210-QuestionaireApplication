package com.questionaire.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "quiz")
public class QuizEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long autoId;
	@Column(nullable = false)
	private String name;
	@Temporal(TemporalType.DATE)
	private Date quizDate;

	@Column(nullable=false)
	private int passPercent;
	@OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<QuestionEntity> question;

	@OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<ResultEntity> results;

	@ManyToOne
	@JoinColumn(name = "code", nullable = false)
	//@JsonIgnore
	private SubjectEntity subject;

	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	//@JsonIgnore
	private TeacherEntity teacher;
}
