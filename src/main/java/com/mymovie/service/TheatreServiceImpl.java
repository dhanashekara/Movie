package com.mymovie.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mymovie.dto.BookingReqDTO;
import com.mymovie.dto.BookingResDTO;
import com.mymovie.entity.Authentication;
import com.mymovie.entity.Seat;
import com.mymovie.entity.Theatre;
import com.mymovie.entity.TheatreMovie;
import com.mymovie.entity.Ticket;
import com.mymovie.exception.MovieManagementException;
import com.mymovie.exception.TicketNotAvailableException;
import com.mymovie.repository.AuthenticationRepository;
import com.mymovie.repository.SeatRepository;
import com.mymovie.repository.TheatreMovieRepository;
import com.mymovie.repository.TheatreRepository;
import com.mymovie.repository.TicketRepository;
import com.mymovie.util.MailWithOTPService;

@Service
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	private TheatreRepository theatreRepository;

	@Autowired
	private SeatRepository seatRepo;

	@Autowired
	private TicketRepository ticketRepo;

	@Autowired
	private AuthenticationRepository authRepo;

	@Autowired
	MailWithOTPService mailWithOTPService;
	
	@Autowired
	TheatreMovieRepository theatreMovieRepo;

	int individualAmount = 0;

	@Override
	public List<Theatre> allTheatre() {

		return theatreRepository.findAll();
	}

	@Override
	public BookingResDTO bookTicket(BookingReqDTO bookingReqDTO) {

		double totalAmount;
		int noOfTickets;

		if (null != bookingReqDTO) {
			
			TheatreMovie tm = theatreMovieRepo.findByMovieIdAndTheatreId(bookingReqDTO.getMovieId(),bookingReqDTO.getTheatreId());
			
			if(null != tm) {
				String category = bookingReqDTO.getCategory();

				noOfTickets = bookingReqDTO.getNoOfTickets();
				if (category.equalsIgnoreCase("gold")) {
					totalAmount = 200 * noOfTickets;
					category = "g";
					individualAmount = 200;
				} else if (category.equalsIgnoreCase("diamond")) {
					totalAmount = 300 * noOfTickets;
					category = "d";
					individualAmount = 300;
				} else {
					totalAmount = 100 * noOfTickets;
					category = "s";
					individualAmount = 100;
				}
				
				//hard coded values
			
				Seat seat1 = new Seat();
				seat1.setSeatId(2);
				seat1.setSeatNo("d2");
				seat1.setTheatreMovieId(1);
				seat1.setAvailable_status("avalable");
				
				Seat seat2 = new Seat();
				seat2.setSeatId(3);
				seat2.setSeatNo("d3");
				seat2.setTheatreMovieId(1);
				seat2.setAvailable_status("avalable");
				

			//	List<Seat> seatList = seatRepo.getSeatDetails(category, noOfTickets);
				List<Seat> seatList = new ArrayList<>();
				
				seatList.add(seat1);
				seatList.add(seat2);
				
				List<Ticket> ticketList = new ArrayList<>();

			//	seatRepo.updateSeats(category, noOfTickets);

				Random rand = new Random();
				int ticketGenId = 1000 + rand.nextInt(9999);

				// mail
				int otp = generateOTPandSendMail(bookingReqDTO.getEmail());
				Authentication auth = new Authentication();

				auth.setOtp(otp);
				authRepo.save(auth);
				auth = authRepo.findByOtp(otp);

				seatList.forEach(seat -> {
					Ticket ticket = new Ticket();
					ticket.setAmount(individualAmount);
					ticket.setBookedDate(bookingReqDTO.getDate());
					ticket.setSeatId(seat.getSeatId());
					ticket.setTheatreMovieId(seat.getTheatreMovieId());
					ticket.setTicketGenId(ticketGenId);
					ticketList.add(ticket);
				});

				ticketRepo.saveAll(ticketList);

				BookingResDTO bookingResDTO = new BookingResDTO();
				bookingResDTO.setOtpGenId(auth.getOtpGen());
				bookingResDTO.setMessage("Ticket Booked successfully");
				bookingResDTO.setStatus("SUCCESS");
				bookingResDTO.setTotalPrice(totalAmount);
				bookingResDTO.setStatusCode(HttpStatus.OK.value());

				return bookingResDTO;

			}else {
				throw new TicketNotAvailableException("Ticket Not Available");
			}
			
		} else {
			throw new MovieManagementException("Please provide Valid data");
		}
	}

	public Integer generateOTPandSendMail(String email) {
		System.out.println(email+"zvczjhvczxvcvzxbn");
		Integer otp = 0;
		try {
			Random rand = new Random();
			otp = 1000 + rand.nextInt(9999);
			String body = "OTP for Ticket Booking is " + otp;
			String subject = "Movie management";
			mailWithOTPService.sendEmail("pradeep.aj28@gmail.com", subject, body);

		} catch (Exception e) {
			throw new MovieManagementException("Error in generating OTP");
		}
		return otp;
	}
}
