package com.safee.AirlineReservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safee.AirlineReservation.model.Flight;
import com.safee.AirlineReservation.repository.FlightDetailsRepository;
import com.safee.AirlineReservation.repository.FlightRepository;

@Service
public class FlightService {
	@Autowired
	//private FlightDao flightDao;
	private FlightRepository flightRepository;
	@Autowired 
	FlightDetailsRepository flightDetailsRepository;
	public void addFlight(Flight flight) {
		// @ResponseBody means the returned String is the response, not a view
		// name
		// @RequestParam means it is a parameter from the GET or POST request
		  
		flightRepository.save(flight);
	}
	public Flight readFlightDetails(Integer flightId)
	{
		return flightRepository.findOne(flightId);
	}
	public void deleteFlightDetails(Integer flightId)
	{
		Flight flight=flightRepository.findOne(flightId);
		flightRepository.delete(flight);
	}
	public void updateFlight(Flight flight)
	{
		Flight flight2=flightRepository.findOne(flight.getFlightId());
	}

}
