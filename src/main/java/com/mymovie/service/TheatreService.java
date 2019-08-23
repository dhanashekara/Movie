package com.mymovie.service;

import java.util.List;

import com.mymovie.dto.BookingReqDTO;
import com.mymovie.dto.BookingResDTO;
import com.mymovie.entity.Theatre;

public interface TheatreService {

	List<Theatre> allTheatre();

	BookingResDTO bookTicket(BookingReqDTO bookingReqDTO);

}
