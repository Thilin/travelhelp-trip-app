package mxhtechnology.travelhelptripapp.controller.impl;
import mxhtechnology.travelhelptripapp.controller.TripController;
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
}
