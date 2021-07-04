package com.example.test02;

import static org.junit.Assert.*;

import org.junit.*;

// 1. 4. 인스턴스 생성
public class ProfileTest {
	private Profile profile;
	private BooleanQuestion question;
	private Criteria criteria;

	// 2. 5. 필드 초기화
	@Before // 테스트 초기화 (JUnit 테스트 실행할 때마다 먼저 실행)
	public void create() {
		profile = new Profile("Bull Hockey, Inc.");
		question = new BooleanQuestion(1, "Got bonuses?");
		criteria = new Criteria();
	}

	// 3. 메서드 실행 후 테스트가 통과 혹은 실패했는지 표기
	@Test
	public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
		// arrange (준비)
		profile.add(new Answer(question, Bool.FALSE));
		criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));
		
		// act (실행)
		boolean matches = profile.matches(criteria);

		// assert (단언)
		// assertFalse(matcher) : matcher가 거짓인지 확인한다
		assertFalse(matches);
	}

	// 6. 메서드 실행 후 테스트가 통과 혹은 실패했는지 표기
	@Test
	public void matchAnswersTrueForAnyDontCareCriteria() {
		// arrange (준비)
		profile.add(new Answer(question, Bool.FALSE));
		criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

		// act (실행)
		boolean matches = profile.matches(criteria);

		// assert (단언)
		// assertTrue(matcher) : matcher가 참인지 확인한다
		assertTrue(matches);
	}
}