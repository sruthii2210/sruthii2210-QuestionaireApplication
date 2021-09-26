package com.questionaire.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class AnswerEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long autoId;

	@OneToOne(targetEntity = QuestionEntity.class)
	@JoinColumn(name = "questionNo")
	@JsonIgnore
	private QuestionEntity question;
	@Column(nullable = false)
	private String option1;
	@Column(nullable = false)
	private String option2;
	@Column(nullable = false)
	private String option3;
	@Column(nullable = false)
	private String option4;
	@Column(nullable = false)
	private Integer crctAns;

}
