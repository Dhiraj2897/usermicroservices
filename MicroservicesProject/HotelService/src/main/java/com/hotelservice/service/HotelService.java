package com.hotelservice.service;

import java.util.List;

import com.hotelservice.Dtos.HotelDto;


public interface HotelService {
	// create Hotel
		HotelDto createHotel(HotelDto hotelDto);

		// getHotel
		HotelDto getHotel(String id);

		// getAllHotel
		List<HotelDto> getAllHotel();
}
