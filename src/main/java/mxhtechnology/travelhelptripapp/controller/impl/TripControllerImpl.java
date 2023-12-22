package mxhtechnology.travelhelptripapp.controller.impl;

import mxhtechnology.travelhelptripapp.controller.TripController;
import mxhtechnology.travelhelptripapp.dto.TripByIdDTO;
import mxhtechnology.travelhelptripapp.dto.TripCreateDTO;
import mxhtechnology.travelhelptripapp.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TripControllerImpl implements TripController {

    @Autowired
    private TripService tripService;

    @Override
    public ResponseEntity<String> createTrip(TripCreateDTO dto) {
        return new ResponseEntity(tripService.createTrip(dto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TripByIdDTO> findTrip(String id, String userId) {
        var response = tripService.findById(id, userId);
        if (response != null)
            return new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<TripByIdDTO> deleteTrip(String id, String userId) {
        var response = tripService.findById(id, userId);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
    }
}
