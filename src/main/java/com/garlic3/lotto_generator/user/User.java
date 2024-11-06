package com.garlic3.lotto_generator.user;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="user")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Cloneable {
	@AllArgsConstructor
	public static enum UserRole {
		ADMIN((byte)0),
		GENERAL((byte)1);

		private final byte value;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;

	@Column(name="username", nullable=false, length=30, unique=true)
	private String username;

	@Column(name="password", nullable=false, length=100, unique=false)
	private String password;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="role", nullable=false)
	private UserRole role;

	@CreationTimestamp
	@Column(name="create_date", nullable=false)
	private LocalDate createDate;

	@CreationTimestamp
	@Column(name="update_date", nullable=false)
	private LocalDate updateDate;

	@Column(name="point", nullable=false)
	private long point;

	@Column(name="delete_date", nullable=true)
	private LocalDate deleteDate;

	@Column(name="nickname", nullable=false, length=20)
	private String nickname;

	@Column(name="email", nullable=false, length=40, unique=true)
	private String email;

	@Column(name="phone", nullable=true, length=11, unique=true)
	private String phone;

	@Override
	public User clone() {
		try {
			return (User)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
