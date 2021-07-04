package com.example.test01;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*; 

import org.junit.Test;

public class ScoreCollectionTest {
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	// arrange (준비) - act (실행) - assert (단언)
	// given (주어진값) - when (무엇을했을때) - then (어떻게됐는가)
	@Test
	public void answersArithmeticMeanOfTwoNumbers() {
		ScoreCollection collection = new ScoreCollection();
		
		collection.add(new Scoreable() {
			@Override
			public int getScore() {
				return 5;
			}
		});
		
		// arrange (준비) : 테스트 상태를 설정
		collection.add(() -> 7);
		
		// act (실행) : 검증해야하는 arithmeticMean 메서드 실행
		int actualResult = collection.arithmeticMean();
		
		// assert (단언) : 기대하는 결과를 단언
		// assertThat(actual:실제결과, matcher:기대되는값) : actual과 matcher를 비교
		// equalTo(matcher) : 실제 기대되는 값을 비교, equalTo() == is()
		assertThat(actualResult, equalTo(6));
	}
}