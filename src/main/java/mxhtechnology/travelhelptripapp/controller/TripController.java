package mxhtechnology.travelhelptripapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import mxhtechnology.travelhelptripapp.dto.TripCreateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/trip")
@CrossOrigin(origins = "*")
public interface TripController {

    @Operation(summary = "Cria nova viagem", description = "Cria uma nova viagem feita pelo usuário")
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @Transactional
    ResponseEntity<String> createTrip(@RequestBody @Validated TripCreateDTO dto);
}
