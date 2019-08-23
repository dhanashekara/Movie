package com.mymovie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymovie.dto.BookingReqDTO;
import com.mymovie.dto.BookingResDTO;
import com.mymovie.entity.Theatre;
import com.mymovie.service.TheatreService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class MovieController {
	
	@Autowired
	private TheatreService theatreService;
	
	
	@GetMapping("/getAllTheatre")
	public ResponseEntity<List<Theatre>> allTheatre(){
		return new ResponseEntity<>(theatreService.allTheatre(),HttpStatus.OK);
	}
	
	@PostMapping("/bookTicket")
	public ResponseEntity<BookingResDTO> bookTicket(@RequestBody BookingReqDTO bookingReqDTO){
		return new ResponseEntity<>(theatreService.bookTicket(bookingReqDTO),HttpStatus.OK);
	}
}
