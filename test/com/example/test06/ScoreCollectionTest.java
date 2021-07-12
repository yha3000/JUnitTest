package com.example.test06;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ScoreCollectionTest {
	private ScoreCollection collection;

	@Before
	public void create() {
		collection = new ScoreCollection();
	}

	@Test
	public void answersArithmeticMeanOfTwoNumbers() {
		collection.add(() -> 5);
		collection.add(() -> 7);

		int actualResult = collection.arithmeticMean();

		assertThat(actualResult, equalTo(6));
	}

	// arthmeticMean()에서 NullPointerException이 발생한다
	@Test(expected = IllegalArgumentException.class)
	public void throwsExceptionWhenAddingNull() {
		collection.add(null);
	}

	// Scoreable 인스턴스가 전혀 없을 수도 있다
	// 0으로 나누기 오류인 ArithmeticException이 발생 할 수도 있다
	@Test
	public void answersZeroWhenNoElementsAdded() {
		assertThat(collection.arithmeticMean(), equalTo(0));
	}

	// 큰 정수 입력을 다룬다면 숫자의 합이 MAX_VALUE를 초과할 수 있다
	@Test
	public void doesNotProperlyHandleIntegerOverflow() {
		collection.add(() -> Integer.MAX_VALUE);
		collection.add(() -> 1);

		assertTrue(collection.arithmeticMean() < 0);
	}
}