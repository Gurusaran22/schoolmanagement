	
package com.guru.depend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Result {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	private int totalquestion;
	private int correctanswer;
	private int totalmarks;
	@ManyToOne
	private Quiz quiz;
	@ManyToOne
	private Students students;
}
