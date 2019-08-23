package com.mymovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovie.entity.TheatreMovie;

public interface TheatreMovieRepository extends JpaRepository<TheatreMovie, Integer>{

	TheatreMovie findByMovieIdAndTheatreId(int movieId, int theatreId);

}
