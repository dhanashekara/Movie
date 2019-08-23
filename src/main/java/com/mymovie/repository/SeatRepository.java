package com.mymovie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.mymovie.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>{

	@Query(value = "update moviedb.seat m set m.available_status = 'booked' where m.seat_no like ':category%' and m.available_status = 'available' limit :noOfTickets",nativeQuery = true)
	void updateSeats(@Param("category") String category, @Param("noOfTickets") int noOfTickets);

	@Query(value = "select * from (select m.seat_id,m.seat_no,m.theatre_movie_id from moviedb.seat m where m.seat_no like 'd%' and m.available_status = 'available')sub limit 2",nativeQuery = true)
	List<Seat> getSeatDetails(@Param("category") String category, @Param("noOfTickets") int noOfTickets);
}
