package mxhtechnology.travelhelptripapp.service;

import mxhtechnology.travelhelptripapp.dto.TripByIdDTO;
import mxhtechnology.travelhelptripapp.dto.TripCreateDTO;

public interface TripService{

    String createTrip(TripCreateDTO dto);

    TripByIdDTO findById(String id, String userId);
}
