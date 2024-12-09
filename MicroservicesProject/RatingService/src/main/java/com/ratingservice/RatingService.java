package com.ratingservice;

import java.util.List;

import com.rating.entities.Rating;


public interface RatingService {

	// create rating
	Rating createRating(Rating rating);

	// get all Ratings by userid
	public List<Rating> getRatingByUserId(String userId);

	// all ratings
	List<Rating> getRatings();
	
	//get all rating by hotel
	
	List<Rating> getRatingByHotelId(String hotelId);

}
