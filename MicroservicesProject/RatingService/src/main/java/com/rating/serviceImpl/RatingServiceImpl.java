package com.rating.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.Repository.RatingRepository;
import com.rating.entities.Rating;

import com.ratingservice.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	@Autowired
	private RatingRepository repository;

	public Rating createRating(Rating rating) {

		return repository.save(rating);
	}

	public List<Rating> getRatingByUserId(String userId) {

		return repository.findByUserId(userId);
	}

	public List<Rating> getRatings() {

		return repository.findAll();
	}

	public List<Rating> getRatingByHotelId(String hotelId) {

		return repository.findByHotelId(hotelId);
	}

}
