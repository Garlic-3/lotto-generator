package com.garlic3.lotto_generator.user;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class UserTest {
	@Test
	public void testClone() {
		User user = User.builder()
			.role(User.UserRole.ADMIN)
			.username("user-id")
			.password("pw haha")
			.id(1001)
			.createDate(LocalDate.now())
			.build();

		User clonedUser = user.clone();

		assert clonedUser != user;
		assert clonedUser.equals(user);
	}
}
