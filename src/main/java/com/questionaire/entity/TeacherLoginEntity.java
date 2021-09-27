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
@Table(name = "login")
public class TeacherLoginEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long autoId;

	@OneToOne(targetEntity = TeacherEntity.class)
	@JoinColumn(name = "id", nullable = false, unique = true)
	@JsonIgnore
	private TeacherEntity teacherId;

	@Column(nullable = false)
	private String password;
}
