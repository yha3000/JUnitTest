package com.example.test08;

import java.io.*;
import java.util.logging.*;
import org.junit.*;
import static org.junit.Assert.*;
import static com.example.test08.ContainsMatches.*;

public class SearchTest {
	private static final String A_TITLE = "1";
	private InputStream stream;

	@Before
	public void turnOffLogging() {
		Search.LOGGER.setLevel(Level.OFF);
	}

	@After
	public void closeResources() throws IOException {
		stream.close();
	}

	@Test
	public void returnsMatchesShowingContextWhenSearchStringInContent() {
		stream = streamOn("rest of text here" + "1234567890search term1234567890" + "more rest of text");
		Search search = new Search(stream, "search term", A_TITLE);
		search.setSurroundingCharacterCount(10);
		search.execute();
		assertThat(search.getMatches(), containsMatches(new Match[] { new Match(A_TITLE, "search term", "1234567890search term1234567890") }));
	}

	@Test
	public void noMatchesReturnedWhenSearchStringNotInContent() {
		stream = streamOn("any text");
		Search search = new Search(stream, "text that doesn't match", A_TITLE);
		search.execute();
		assertTrue(search.getMatches().isEmpty());
	}

	@Test
	public void returnsErroredWhenUnableToReadStream() {
		stream = createStreamThrowingErrorWhenRead();
		Search search = new Search(stream, "", "");
		search.execute();
		assertTrue(search.errored());
	}

	private InputStream createStreamThrowingErrorWhenRead() {
		return new InputStream() {
			@Override
			public int read() throws IOException {
				throw new IOException();
			}
		};
	}

	@Test
	public void erroredReturnsFalseWhenReadSucceeds() {
		stream = streamOn("");
		Search search = new Search(stream, "", "");
		search.execute();
		assertFalse(search.errored());
	}

	private InputStream streamOn(String pageContent) {
		return new ByteArrayInputStream(pageContent.getBytes());
	}
}