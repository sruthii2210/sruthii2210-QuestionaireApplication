package com.questionaire.entity;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="quiz")
public class QuizEntity implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long autoId;
	@Column(nullable=false)
	private String name;
	
	@OneToMany(mappedBy="quiz",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Question> question;

	@OneToMany(mappedBy="quiz",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Result> results;

	
	@ManyToOne(targetEntity=SubjectEntity.class,fetch=FetchType.LAZY)
	@JoinColumn(name="subCode",nullable=false)
	@JsonIgnore
	private SubjectEntity subject;
	
	@ManyToOne(targetEntity=Teacher.class,fetch=FetchType.LAZY)
	@JoinColumn(name="id",nullable=false)
	@JsonIgnore
	private Teacher teacher;
}
