package mxhtechnology.travelhelptripapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mxhtechnology.travelhelptripapp.dto.TripByIdDTO;
import mxhtechnology.travelhelptripapp.dto.TripCreateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/trip")
@CrossOrigin(origins = "*")
@Tag(name = "Trip Controller")
public interface TripController {

    @Operation(summary = "Create Trip", description = "Create a new Trip")
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @Transactional
    ResponseEntity<String> createTrip(@RequestBody @Validated TripCreateDTO dto);

    @Operation(summary = "Find Trip", description = "Find a Trip by Id")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<TripByIdDTO> findTrip(
            @RequestParam String id,
            @RequestParam String userId);
}
