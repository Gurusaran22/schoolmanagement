package com.guru.depend.dto;
 
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDTO {
	private Long id;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	
	
}