package com.safee.AirlineReservation.repository;

import org.springframework.data.repository.CrudRepository;

import com.safee.AirlineReservation.model.Flight;

public interface FlightRepository  extends CrudRepository<Flight, Integer> {

}
