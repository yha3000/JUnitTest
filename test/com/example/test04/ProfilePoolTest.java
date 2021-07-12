package com.example.test04;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

import com.example.test04.Answer;
import com.example.test04.Bool;
import com.example.test04.BooleanQuestion;
import com.example.test04.Criteria;
import com.example.test04.Criterion;
import com.example.test04.Profile;
import com.example.test04.ProfilePool;
import com.example.test04.Question;
import com.example.test04.Weight;

import static org.hamcrest.CoreMatchers.*;

public class ProfilePoolTest {
	private ProfilePool pool;
	private Profile langrsoft;
	private Profile smeltInc;
	private BooleanQuestion doTheyReimburseTuition;

	@Before
	public void create() {
		pool = new ProfilePool();
		langrsoft = new Profile("Langrsoft");
		smeltInc = new Profile("Smelt Inc.");
		doTheyReimburseTuition = new BooleanQuestion("Reimburses tuition?");
	}

	@Test
	public void scoresProfileInPool() {
		langrsoft.add(new Answer(doTheyReimburseTuition, Bool.TRUE));
		pool.add(langrsoft);

		pool.score(soleNeed(doTheyReimburseTuition, Bool.TRUE, Weight.Important));

		assertThat(langrsoft.score(), equalTo(Weight.Important.getValue()));
	}

	private Criteria soleNeed(Question question, int value, Weight weight) {
		Criteria criteria = new Criteria();
		criteria.add(new Criterion(new Answer(question, value), weight));
		return criteria;
	}

	@Test
	public void answersResultsInScoredOrder() {
		smeltInc.add(new Answer(doTheyReimburseTuition, Bool.FALSE));
		pool.add(smeltInc);
		langrsoft.add(new Answer(doTheyReimburseTuition, Bool.TRUE));
		pool.add(langrsoft);

		pool.score(soleNeed(doTheyReimburseTuition, Bool.TRUE, Weight.Important));
		List<Profile> ranked = pool.ranked();

		assertThat(ranked.toArray(), equalTo(new Profile[] { langrsoft, smeltInc }));
	}
}
