package com.mymovie.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingResDTO {

	String message;
	int statusCode;
	String status;
	double totalPrice;
	int otpGenId;
	
}
