package mxhtechnology.travelhelptripapp.service;

import mxhtechnology.travelhelptripapp.dto.TripByIdDTO;
import mxhtechnology.travelhelptripapp.dto.TripCreateDTO;
import mxhtechnology.travelhelptripapp.entity.Trip;

public interface TripService{

    String createTrip(TripCreateDTO dto);

    TripByIdDTO findById(String id, String userId);

    void deleteTrip(Trip trip);
}
