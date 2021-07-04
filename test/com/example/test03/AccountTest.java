package com.example.test03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountTest {
	private Account account;

	@Before // 테스트 초기화 (JUnit 테스트 실행할 때마다 먼저 실행)
	public void createAccount() {
		account = new Account("an account name");
	}

	@Test
	public void hasPositiveBalance() {
		// arrange (준비)
		account.deposit(50);
		// act (실행) // assert (단언)
		// assertTrue(a) : a가 참인지 확인한다
		assertTrue(account.hasPositiveBalance());
	}

	@Test
	public void depositIncreasesBalance() {
		// arrange (준비)
		int initialBalance = account.getBalance();
		account.deposit(100);
		// act (실행) // assert (단언)
		// assertTrue(a) : a가 참인지 확인한다
		assertTrue(account.getBalance() > initialBalance);
		// assertThat(actual:실제결과, matcher:기대되는값) : actual과 matcher를 비교
		// equalTo(matcher) : 어떠한 타입도 비교가 가능하다
		assertThat(account.getBalance(), equalTo(100)); // 계좌의 잔고가 100과 같아야 한다.
	}

	@Test
	public void depositIncreasesBalance_hamcrestAssertTrue() {
		// arrange (준비)
		account.deposit(50);
		// act (실행) // assert (단언)
		// assertThat(actual:실제결과, matcher:기대되는값) : actual과 matcher를 비교
		// is(matcher) : 넘겨받은 matcher를 반환만 한다
		assertThat(account.getBalance() > 0, is(true)); // 계좌의 잔고가 0보다 크다 (true)
	}

	@Ignore // Test 무시
	@ExpectToFail
	@Test
	public void assertFailure() {
		// account.getName() : an account name
		// an account name 과 xyz와는 같지 않으므로 에러
		// assertTrue(a) : a가 참인지 확인한다
		assertTrue(account.getName().startsWith("xyz"));
		// assertThat(actual:실제결과, matcher:기대되는값) : actual과 matcher를 비교
		assertThat(account.getName(), startsWith("xyz"));
	}

	@Ignore // Test 무시
	@ExpectToFail
	@Test
	public void comparesFailing() {
		// 자바의 배열이나 객체를 비교할때는 equalTo() 메서드를 사용
		// assertThat(actual:실제결과, matcher:기대되는값) : actual과 matcher를 비교
		assertThat(new String[] { "a", "b", "c" }, equalTo(new String[] { "a", "b" }));
		assertThat(Arrays.asList(new String[] { "a" }), equalTo(Arrays.asList(new String[] { "a", "ab" })));
	}

	@Test
	public void comparesArraysPassing() {
		// 자바의 배열이나 객체를 비교할때는 equalTo() 메서드를 사용
		// assertThat(actual:실제결과, matcher:기대되는값) : actual과 matcher를 비교
		assertThat(new String[] { "a", "b" }, equalTo(new String[] { "a", "b" }));
		assertThat(Arrays.asList(new String[] { "a" }), equalTo(Arrays.asList(new String[] { "a" })));
	}

	@Test
	public void variousMatcherTests() {
		Account account = new Account("my big fat acct");
		// is("my big fat acct") == equalTo("my big fat acct")
		// is()는 없어도 되므로 가능하면 equalTo()를 사용한다
		assertThat(account.getName(), is(equalTo("my big fat acct")));
		// 부정하는 단언을 만든다면 not()을 사용
		// is(not(equalTo("plunderings")))로 표현 가능
		assertThat(account.getName(), not(equalTo("plunderings")));
		// 비교값이 null이 아닐경우 테스트 통과
		assertThat(account.getName(), is(not(nullValue())));
		assertThat(account.getName(), is(notNullValue()));
		// account.getName()이 null을 반환하면 equalTo()는 테스트하지 않음
		assertThat(account.getName(), equalTo("my big fat acct"));
		// allOf(matcher, matcher) : 내부에 선언된 모든 매쳐가 정상일 경우 통과
		assertThat(account.getName(), allOf(startsWith("my"), endsWith("acct")));
		// anyOf(matcher, matcher) : 내부에 선언된 매쳐중 하나 이상 통과할 경우 통과
		assertThat(account.getName(), anyOf(startsWith("my"), endsWith("loot")));
		// both : A and B 형식으로 matcher를 사용
		// A, B 둘다 통과할 경우 테스트 성공
		assertThat(account.getName(), both(containsString("m")).and(containsString("y")));
		// either : A or B 형식으로 matcher를 사용
		// A, B 둘 중 하나가 성공할 경우 테스트 성공
		assertThat(account.getName(), either(containsString("m")).or(containsString("d")));
		// isA : 비교되는 값이 특정 클래스일 경우 테스트 통과
		assertThat(account.getName(), isA(String.class));
	}

	@Test
	public void moreMatcherTests() {
		Account account = new Account(null);
		// 비교값이 null일 경우 테스트 통과
		assertThat(account.getName(), is(nullValue()));
	}

	@Test
	public void items() {
		List<String> names = new ArrayList<>();
		names.add("Moe");
		names.add("Larry");
		names.add("Curly");
		// hasItem : 배열에서 매쳐가 통과하는 값이 하나 이상이 있는지 여부를 검사
		assertThat(names, hasItem("Curly"));
		assertThat(names, hasItem(endsWith("y")));
		// hasItems : 배열에서 매쳐리스트에 선언된 값들 모두가 하나 이상 있는지 여부를 검사
		assertThat(names, hasItems("Curly", "Moe"));
		// everyItem : 배열이나 리스트를 순회하며 매쳐가 실행
		assertThat(names, not(everyItem(endsWith("y"))));
	}

	@Ignore // Test 무시
	@ExpectToFail
	@Test
	public void assertDoublesEqual() {
		// 6.9599999 != 6.96
		assertThat(2.32 * 3, equalTo(6.96));
	}

	@Test
	public void assertDoublesCloseTo() {
		// hamcreat-all-1.3.jar이 필요함
		// closeTo() 부동소수점 수를 훨씬 수월하게 비교할 수 있음
		assertThat(2.32 * 3, closeTo(6.96, 0.0005));
	}

	@Ignore // Test 무시
	@Test
	public void testWithWorthlessAssertionComment() {
		account.deposit(50);
		// asertThat(message, actual, matcher) : message는 단언의 근거를 설명
		assertThat("account balance is 100", account.getBalance(), equalTo(50));
	}

	// expected 예외처리
	@Test(expected = InsufficientFundsException.class)
	public void throwsWhenWithdrawingTooMuch() {
		account.withdraw(100);
	}

	// try-catch 예외처리
	@Test
	public void throwsWhenWithdrawingTooMuchTry() {
		try {
			account.withdraw(100);
			fail();
		} catch (InsufficientFundsException expected) {
			assertThat(expected.getMessage(), equalTo("balance only 0"));
		}
	}
	
	// ExpectedException 규칙은 예외를 검사하는데 있어서 단순한 방식과 옛방식의 좋은점만 모음
	@Rule // 규칙을 사용하려면 Rule Annotation 부착
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void exceptionRule() {
		// 발생할수 있는 일을 규칙에 알린다
		thrown.expect(InsufficientFundsException.class);
		// 예외상황에 적절한 메세지를 지정
		thrown.expectMessage("balance only 0");

		account.withdraw(100);
	}
	
	// 예외 무시
	@Test
	public void readsFromTestFile() throws IOException {
		String filename = "test.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write("test data");
		writer.close();
	}
}