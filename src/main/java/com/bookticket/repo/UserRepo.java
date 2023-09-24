package com.bookticket.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookticket.domain.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	Optional<User> findBySeatNo(Integer seatNo);

	User findByOtp(Integer otp);

}
