package com.gpfaltz.travelorder;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Inject;

@Readiness
public class ReadinessCheck implements HealthCheck {

	@RestClient
	@Inject
	FlightService flightService;

	@RestClient
	@Inject
	HotelService hotelService;

	@Override
	public HealthCheckResponse call() {

		if ((flightService.findById(1) != null) && (hotelService.findById(1) != null)) {
			return HealthCheckResponse.up("I'm Ready.");
		} else {
			return HealthCheckResponse.down("I'm not Ready.");
		}

	}

}
