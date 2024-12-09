package com.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelservice.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
