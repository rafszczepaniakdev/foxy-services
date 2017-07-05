package com.foxy.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foxy.domain.sql.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
