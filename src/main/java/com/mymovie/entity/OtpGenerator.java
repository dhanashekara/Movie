package com.mymovie.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OtpGenerator {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int otpGenId;
	private int otp;
}
