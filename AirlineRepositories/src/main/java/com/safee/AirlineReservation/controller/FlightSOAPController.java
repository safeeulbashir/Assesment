package com.safee.AirlineReservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.safee.AirlineReservation.response.GetFlightRequest;
import com.safee.AirlineReservation.response.GetFlightResponse;
import com.safee.AirlineReservation.service.FlightService;

@Endpoint
public class FlightSOAPController {
	private static final String NAMESPACE_URI = "http://www.safee.com/response";
	@Autowired
	FlightService flightService;
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeRequest")
	@ResponsePayload
	public GetFlightResponse getCountry(@RequestPayload GetFlightRequest request) {
		GetFlightResponse response = new GetFlightResponse();
		//response.setCountry(countryRepository.findCountry(request.getName()));
		response.setflight(flightService.readFlightDetails(request.getflightId()));
		return response;
	}

}
