package com.guru.depend.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizSubmissionDTO {

	private Long StudentId;
	private Long quizId;
	private List<AnswerDTO> sanswer;
}
