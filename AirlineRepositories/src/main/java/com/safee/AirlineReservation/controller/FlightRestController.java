package com.safee.AirlineReservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safee.AirlineReservation.model.Flight;
import com.safee.AirlineReservation.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightRestController {
	private static final Logger logger = LoggerFactory.getLogger(FlightRestController.class);
	FlightService flightService = new FlightService();

	@RequestMapping("/{flightId}")
		public Flight viewFlight(@PathVariable Integer flightId) {
		//return new Greeting(counter.incrementAndGet(), String.format(template, name));
		Flight flight = flightService.readFlightDetails(flightId);
		return flight;
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addInformation(@RequestBody Flight flight) {
		
		//model.put("message", "Employee added Successfully");
		//logger.debug("Employee Salary " + employeesAllInformation.getFirstName());
		flightService.addFlight(flight);
		return "Employee Added";
	}
	
	

}
