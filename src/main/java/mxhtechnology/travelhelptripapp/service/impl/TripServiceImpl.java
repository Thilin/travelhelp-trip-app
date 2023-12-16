package mxhtechnology.travelhelptripapp.service.impl;

import mxhtechnology.travelhelptripapp.dto.TripCreateDTO;
import mxhtechnology.travelhelptripapp.entity.Trip;
import mxhtechnology.travelhelptripapp.repository.TripRepository;
import mxhtechnology.travelhelptripapp.service.TripService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository){
        this.tripRepository = tripRepository;
    }

    @Override
    public String createTrip(TripCreateDTO dto) {
        var trip = new Trip();
        trip.setId(UUID.randomUUID().toString());
        trip.setUserId(dto.getUserId());
        trip.setTripName(dto.getTripName());
        trip.setCity(dto.getCity());
        trip.setState(dto.getState());
        trip.setObservations(dto.getObservations());
        trip.setDateEnd(dto.getDateEnd());
        trip.setDateStart(dto.getDateStart());

        tripRepository.save(trip);
        return trip.getId();
    }
}
