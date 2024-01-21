package mxhtechnology.travelhelptripapp.service.impl;

import mxhtechnology.travelhelptripapp.dto.TripByIdDTO;
import mxhtechnology.travelhelptripapp.dto.TripCreateDTO;
import mxhtechnology.travelhelptripapp.entity.Trip;
import mxhtechnology.travelhelptripapp.infrastructure.aws.dynamodb.repository.TripRepository;
import mxhtechnology.travelhelptripapp.service.TripService;
import org.springframework.stereotype.Service;

import java.util.List;
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
        trip.setUserId(dto.getUserId()+"-"+ UUID.randomUUID().toString());
        trip.setTripName(dto.getTripName());
        trip.setCity(dto.getCity());
        trip.setState(dto.getState());
        trip.setCountry(dto.getCountry());
        trip.setObservations(dto.getObservations());
        trip.setDateEnd(dto.getDateEnd().toString());
        trip.setDateStart(dto.getDateStart().toString());

        return tripRepository.save(trip);
    }

    @Override
    public TripByIdDTO findById(String id, String userId) {
        var trip = tripRepository.findById(id, userId);
        if (trip != null){
            var dto = new TripByIdDTO();
            dto.setCountry(trip.getCountry());
            dto.setState(trip.getState());
            dto.setCity(trip.getCity());
            dto.setTripName(trip.getTripName());
            dto.setObservations(trip.getObservations());
            dto.setDateEnd(trip.getDateEnd());
            dto.setDateStart(trip.getDateStart());
            return dto;
        }
        return null;
    }

    @Override
    public void deleteTrip(String id, String userId) {
        var trip = tripRepository.findById(id, userId);
        if(trip == null || trip.getId() == null)
            throw new IllegalArgumentException("Trip id cant be null");
        tripRepository.delete(trip);
    }

    @Override
    public List<Trip> listAll(String pk, String sk) {
        return tripRepository.listByUser(pk, sk);
    }
}
