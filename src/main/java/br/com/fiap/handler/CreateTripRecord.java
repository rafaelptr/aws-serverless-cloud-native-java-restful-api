package br.com.fiap.handler;

import java.io.IOException;

import br.com.fiap.model.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.dao.TripRepository;

public class CreateTripRecord implements RequestHandler<HandlerRequest, HandlerResponse> {
	
	private final TripRepository repository = new TripRepository();

	@Override
	public HandlerResponse handleRequest(final HandlerRequest request, final Context context) {

		Trip trip = null;
		try {
			trip = new ObjectMapper().readValue(request.getBody(), Trip.class);
		} catch (IOException e) {
			return HandlerResponse.builder().setStatusCode(400).setRawBody("There is a error in your Trip!").build();
		}

		PhotoBucket photoBucket = new PhotoBucket(trip.getCountry(), trip.getCity(), trip.getDate());
		trip.setUrl(photoBucket.getName());

		context.getLogger().log("Creating a new trip id, url: " + trip.getId()+", "+trip.getUrl());

		final Trip tripRecorded = repository.save(trip);

		CreateTripDTO tripDTO = new CreateTripDTO(trip.getId(),trip.getUrl());

		return HandlerResponse.builder().setStatusCode(201).setObjectBody(tripDTO).build();
	}
}