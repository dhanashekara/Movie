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
public class Shows {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int showsId;
	private int theatreMovieId;
	private String showsName;
}
