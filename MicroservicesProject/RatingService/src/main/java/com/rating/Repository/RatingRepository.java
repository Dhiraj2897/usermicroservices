package com.rating.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rating.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating,String> {
	//custom finder method
	List<Rating> findByUserId(String userId);

	List<Rating> findByHotelId(String hotelId);
}
