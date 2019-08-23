package com.mymovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymovie.entity.Authentication;
@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

	Authentication findByOtp(int otp);

}
