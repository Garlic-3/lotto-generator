package com.garlic3.lotto_generator.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
	@Autowired
	UserRepository userRepository;

	@Autowired
	private EntityManager entityManager;

	@AfterEach
	public void end() {
		userRepository.deleteAll();
	}

	@Test
	public void testSaveUser() {
		User user = User.builder()
			.username("adminUser")
			.password("securePassword")
			.role(User.UserRole.ADMIN)
			.point(1000)
			.nickname("admin")
			.email("jfaw@aaa.com")
			.phone("01012341234")
			.build();

		User savedUser = userRepository.save(user);
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
		assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
		assertThat(savedUser.getRole()).isEqualTo(user.getRole());
		assertThat(savedUser.getPoint()).isEqualTo(user.getPoint());
		assertThat(savedUser.getNickname()).isEqualTo(user.getNickname());
		assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
		assertThat(savedUser.getPhone()).isEqualTo(user.getPhone());
	}

	@Test
	public void testDeleteUser() {
		User user = User.builder()
			.username("User")
			.password("securePassword")
			.role(User.UserRole.GENERAL)
			.point(1000)
			.nickname("admin")
			.email("jfaw@aaa.com")
			.phone("01012341234")
			.build();

		User savedUser = userRepository.save(user);
		Long userId = savedUser.getId();

		userRepository.deleteById(userId);
		assertThat(userRepository.existsById(userId)).isFalse();
	}

	@Test
	public void testUniqueUsername() {
		User user1 = User.builder()
			.username("duplicateUser")
			.password("password123")
			.role(User.UserRole.GENERAL)
			.point(1000)
			.nickname("duplicate")
			.email("aaa@aaa.com")
			.phone("01012341234")
			.build();

		User user2 = User.builder()
			.username("duplicateUser") // same username as user1
			.password("anotherPassword")
			.role(User.UserRole.ADMIN)
			.point(990)
			.nickname("duplicate2")
			.email("bbb@bbb.com")
			.phone("01043214321")
			.build();

		userRepository.save(user1);
		assertThrows(Exception.class, () -> {
			try {
				userRepository.save(user2);
			} catch (Exception e) {
				entityManager.clear(); // 예외 후 세션 초기화
				throw e; // 예외를 다시 던져 테스트 실패를 유지
			}
		});
	}

	@Test
	public void testIdGenerator() {
		User user1 = User.builder()
			.username("user1")
			.password("password123")
			.role(User.UserRole.GENERAL)
			.point(1000)
			.nickname("duplicate")
			.email("aaa@aaa.com")
			.phone("01012341234")
			.build();

		User user2 = User.builder()
			.username("user2")
			.password("anotherPassword")
			.role(User.UserRole.ADMIN)
			.point(990)
			.nickname("duplicate2")
			.email("bbb@bbb.com")
			.phone("01043214321")
			.build();

		User savedUser1 = userRepository.save(user1);
		User savedUser2 = userRepository.save(user2);

		assertThat(savedUser1.getId()).isEqualTo(savedUser2.getId()-1);
	}

	@Test
	public void testCreationTimestamp() {
		User user = User.builder()
			.username("user")
			.password("password123")
			.role(User.UserRole.GENERAL)
			.point(1000)
			.nickname("duplicate")
			.email("aaa@aaa.com")
			.phone("01012341234")
			.build();

		User savedUser = userRepository.save(user);

		assertThat(savedUser.getCreateDate()).isEqualTo(savedUser.getUpdateDate());
	}
}
