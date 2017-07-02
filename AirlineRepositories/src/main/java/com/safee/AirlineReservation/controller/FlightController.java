package com.safee.AirlineReservation.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.safee.AirlineReservation.model.Flight;
import com.safee.AirlineReservation.service.FlightService;

/**
 * Hello world!
 *
 */
@Controller
public class FlightController {

	private static final Logger logger = LoggerFactory.getLogger(FlightController.class);
	@Autowired
	FlightService flightService;
	// inject via application.properties

	@InitBinder("flight")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		logger.debug("Binder called for employee");

	}

	@RequestMapping("/")

	public String welcome(Map<String, Object> model) {
		//model.put("message", this.message);
		return "index";
	}

	@RequestMapping(value = "/viewFlight")
	public String getFlight(Model model) {
		// model.put("message", this.message);
		return "view";
	}

	@RequestMapping(value = "/viewFlight/{flightId}")
	public String view(@PathVariable Integer flightId, Model model) {
		Flight flight = flightService.readFlightDetails(flightId);
		((Map<String, Object>) model).put("flight", flight);
		return "view";
	}

	@RequestMapping(value = "/updateFlight")
	public String getUpdateFlight(Model model) {
		return "update";
	}

	@RequestMapping(value = "/updateFlight/{flightId}")
	public String updateEmployee(@PathVariable Integer flightId, Model model) {
		Flight flight = flightService.readFlightDetails(flightId);
		((Map<String, Object>) model).put("flight", flight);
		return "update";
	}

	@ModelAttribute("flight")
	public Flight getFlghtForView(Model Model) {
		Flight flight = new Flight();
		return flight;
	}

	@RequestMapping(value = "/updateFlightInformation", method = RequestMethod.POST)
	public String updateFlightInformation(@ModelAttribute("Flight")Flight flight, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			return "updateEmployee";
		}
		flightService.addFlight(flight);

		model.put("message", "Message From Heaven");//
		return "view";
	}

	@RequestMapping(value = "/addFlight")
	public String addEmployeeInformation(ModelMap model) {
		return "addEmployee";
	}

	@RequestMapping(value = "/addInformation", method = RequestMethod.POST)
	public String addInformation(@ModelAttribute("flight") Flight flight, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			logger.error("Model not mapped. Error from addInformation");
			return "addFlight";
		}
		model.put("message", "Employee added Successfully");
		flightService.addFlight(flight);
		return "view";
	}


}        
