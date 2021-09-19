package com.questionaire.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Teacher")
public class Teacher {
	@Id
	private Long id;
	
	@OneToMany(mappedBy="teacher",fetch=FetchType.LAZY)
	private Set<TeacherSubject> teacherSub;

	@OneToOne(mappedBy="userid")
	private TeacherLogin staffId;
	
	@OneToMany(mappedBy="teacher",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Quiz> quiz;
	
	@NotNull
	@Size(max=20)
	private String firstName;
	@NotNull
	@Size(max=20)
	private String lastName;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	@NotNull
	@Size(max=7)
	private String gender;
	@NotNull
	@Size(max=10)
	private String qualification;
	@Email
	@NotNull
	private String email;
	@NotNull
	private Long contactNo;
	@NotNull
	private String address;
	
	public Teacher(Long id, @NotNull @Size(max = 20) String firstName, @NotNull @Size(max = 20) String lastName,
			@NotNull Date dateOfBirth, @NotNull @Size(max = 7) String gender,
			@NotNull @Size(max = 10) String qualification, @Email @NotNull String email, @NotNull Long contactNo,
			@NotNull String address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.qualification = qualification;
		this.email = email;
		this.contactNo = contactNo;
		this.address = address;
	}
	
}
