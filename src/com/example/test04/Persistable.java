package com.example.test04;

import java.time.*;

public interface Persistable {
	Integer getId();

	void setId(Integer id);

	Instant getCreateTimestamp();

	void setCreateTimestamp(Instant instant);
}
