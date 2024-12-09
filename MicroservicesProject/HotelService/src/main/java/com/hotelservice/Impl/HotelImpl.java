package com.hotelservice.Impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelservice.Dtos.HotelDto;
import com.hotelservice.entities.Hotel;
import com.hotelservice.exceptions.ResourceNotFoundException;
import com.hotelservice.repository.HotelRepository;
import com.hotelservice.service.HotelService;

@Service
public class HotelImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	public HotelImpl(HotelRepository hotelRepository) {
		super();
		this.hotelRepository = hotelRepository;
	}
	// convert Entity to dto

	private HotelDto mapToDto(Hotel hotel) {
		HotelDto hotelDto = new HotelDto();
		hotelDto.setId(hotel.getId());
		hotelDto.setName(hotel.getName());
		hotelDto.setLocation(hotel.getLocation());
		hotelDto.setAbout(hotel.getAbout());
		return hotelDto;

	}

	// DTO to Entity
	private Hotel mapToEntity(HotelDto hotelDto) {
		Hotel hotel = new Hotel();
		hotel.setId(hotelDto.getId());
		hotel.setName(hotelDto.getName());
		hotel.setLocation(hotelDto.getLocation());
		hotel.setAbout(hotelDto.getAbout());
		return hotel;

	}

	@Override
	public HotelDto createHotel(HotelDto hotelDto) {
//generate unique id

		// convert dto to entity

		Hotel hotel = mapToEntity(hotelDto);
		Hotel newHotel = hotelRepository.save(hotel);
		String id = UUID.randomUUID().toString();
		hotel.setId(id);
		// convert entity to dto
		HotelDto saveHotel = mapToDto(newHotel);

		return saveHotel;

	}

	@Override
	public HotelDto getHotel(String id) {
		Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id is not found"));
		return mapToDto(hotel);
	}

	@Override
	public List<HotelDto> getAllHotel() {
		List<Hotel> Hotels = hotelRepository.findAll();
		return Hotels.stream().map(hotel->mapToDto(hotel)).collect(Collectors.toList());
	}

}
