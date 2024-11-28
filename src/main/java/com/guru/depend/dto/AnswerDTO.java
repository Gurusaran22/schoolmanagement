package com.guru.depend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerDTO {

	private Long questionId;
	private String Sanswer;
}
