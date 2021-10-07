package com.questionaire.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "question")
public class QuestionEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer questionNo;
	@Column(nullable = false)
	private String question;

	@OneToOne(mappedBy = "question")
	private AnswerEntity answer;

	@ManyToOne(targetEntity = QuizEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private QuizEntity quiz;

}
