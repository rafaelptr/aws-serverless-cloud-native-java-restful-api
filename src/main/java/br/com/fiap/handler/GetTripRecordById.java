package br.com.fiap.handler;

import java.util.List;
import java.util.Optional;

import br.com.fiap.dao.TripRepository;
import br.com.fiap.model.HandlerRequest;
import br.com.fiap.model.HandlerResponse;
import br.com.fiap.model.Trip;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class GetTripRecordById implements RequestHandler<HandlerRequest, HandlerResponse> {

	private final TripRepository repository = new TripRepository();

	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {

		final String id = request.getPathParameters().get("id");

		context.getLogger().log("Searching for trip id: " + id);

		final Optional<Trip> optionalTrip = this.repository.findById(id);

		return optionalTrip
				.map((trip) -> HandlerResponse.builder().setStatusCode(200).setObjectBody(trip).build())
				.orElse(HandlerResponse.builder().setStatusCode(404).build());
	}
}