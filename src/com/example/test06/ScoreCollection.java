package com.example.test06;

import java.util.ArrayList;
import java.util.List;

public class ScoreCollection {
	private List<Scoreable> scores = new ArrayList<>();

	// 보호절(guard clause)을 넣어 입력 범위를 분명하게 한다
	public void add(Scoreable scoreable) {
		if (scoreable == null) throw new IllegalArgumentException();
		scores.add(scoreable);
	}

	// 컬렉션이 비었을 때 0 값을 기대한다고 명시한다
	public int arithmeticMean() {
		if (scores.size() == 0) return 0;
		
		int total = scores.stream().mapToInt(Scoreable::getScore).sum();
		return total / scores.size();
	}
}