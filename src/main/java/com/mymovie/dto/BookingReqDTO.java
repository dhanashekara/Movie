package com.mymovie.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingReqDTO {

	int theatreId;
	int movieId;
	Date date;
	int showId;
	String category;
	int noOfTickets;
	String email;
}
