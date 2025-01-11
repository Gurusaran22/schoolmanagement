package com.guru.depend.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizSubmissionDTO {

	private Long studentId;
	private Long quizId;
	private List<AnswerDTO> sanswer;

//	public Long getStudentId() {
//		return studentId;
//	}
//
//	public void setStudentId(Long studentId) {
//		this.studentId= studentId;
//	}
//
//	public Long getQuizId() {
//		return quizId;
//	}
//
//	public void setQuizId(Long quizId) {
//		this.quizId = quizId;
//	}

	public List<AnswerDTO> getAnswers() {
		return sanswer;
	}

	public void setAnswers(List<AnswerDTO> answers) {
		this.sanswer = answers;
	}
}
