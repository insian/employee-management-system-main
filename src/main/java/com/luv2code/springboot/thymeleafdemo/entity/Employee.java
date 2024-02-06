package com.luv2code.springboot.thymeleafdemo.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="employee_soham")
public class Employee {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	@NotBlank(message = "first name should not be blank")
	@Length(min = 4, max = 30, message = "account holder name is wrong")
	private String firstName;
	
	@Column(name="last_name")
	@NotBlank(message = "first name should not be blank")
	@Length(min = 2, max = 30, message = "account holder name is wrong")
	private String lastName;
	
	@Column(name="email", unique = true)
	@Email(message = "invalid email",regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String email;

	public Employee(
			@NotBlank(message = "first name should not be blank") @Length(min = 4, max = 30, message = "account holder name is wrong") String firstName,
			@NotBlank(message = "first name should not be blank") @Length(min = 4, max = 30, message = "account holder name is wrong") String lastName,
			@Email(message = "invalid email", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
		

}











