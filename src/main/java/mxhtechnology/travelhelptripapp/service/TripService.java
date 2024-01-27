package mxhtechnology.travelhelptripapp.service;

import mxhtechnology.travelhelptripapp.dto.TripByIdDTO;
import mxhtechnology.travelhelptripapp.dto.TripCreateDTO;
import mxhtechnology.travelhelptripapp.entity.Trip;

import java.util.List;

public interface TripService{

    String createTrip(TripCreateDTO dto);

    TripByIdDTO findById(String id, String userId);

    void deleteTrip(String id, String user);

    List<TripByIdDTO> listAll(String pk, String sk);
}