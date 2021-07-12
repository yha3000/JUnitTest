package com.example.test05.domain;

import java.util.*;
import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "percentile")
public class PercentileQuestion extends Question {
	private static final long serialVersionUID = 1L;

	@ElementCollection
	@CollectionTable(name = "AnswerChoice", joinColumns = @JoinColumn(name = "question_id"))
	private List<String> answerChoices;

	public PercentileQuestion() {
	}

	public PercentileQuestion(String text, String[] answerChoices) {
		super(text);
		this.answerChoices = Arrays.asList(answerChoices);
	}

	public List<String> getAnswerChoices() {
		return answerChoices;
	}

	public void setAnswerChoices(List<String> answerChoices) {
		this.answerChoices = answerChoices;
	}

	@Override
	public boolean match(int expected, int actual) {
		return expected <= actual;
	}
}
