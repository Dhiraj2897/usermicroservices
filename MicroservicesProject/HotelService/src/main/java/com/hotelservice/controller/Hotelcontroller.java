package com.hotelservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelservice.Dtos.HotelDto;
import com.hotelservice.service.HotelService;


@RestController
@RequestMapping("/hotels")
public class Hotelcontroller {

	@Autowired
	HotelService hotelservice;

	public Hotelcontroller(HotelService hotelservice) {
		super();
		this.hotelservice = hotelservice;
	}

	@PostMapping
	public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto) {

		return new ResponseEntity<HotelDto>(hotelservice.createHotel(hotelDto), HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<HotelDto> getHotel(@PathVariable String id) {
		return ResponseEntity.ok(hotelservice.getHotel(id));
	}

	@GetMapping
	public List<HotelDto> getAllUsers() {
		return hotelservice.getAllHotel();
	}
}
