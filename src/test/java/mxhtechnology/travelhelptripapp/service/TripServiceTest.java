package mxhtechnology.travelhelptripapp.service;

import mxhtechnology.travelhelptripapp.dto.TripCreateDTO;
import mxhtechnology.travelhelptripapp.entity.Trip;
import mxhtechnology.travelhelptripapp.repository.TripRepository;
import mxhtechnology.travelhelptripapp.service.impl.TripServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class TripServiceTest {

    TripService tripService;

    @MockBean
    TripRepository tripRepository;

    @BeforeEach
    public void setUp(){
        this.tripService = new TripServiceImpl(tripRepository);
    }

    @Test
    @DisplayName("Should Create a new trip")
    public void createTripTest(){
        Trip trip = tripBuilder();
        Mockito.when(tripRepository.save(trip)).thenReturn(trip);

        var dto = tripDTOBuilder();
        var tripCreated = tripService.createTrip(dto);

        assertThat(tripCreated).isNotEmpty();
    }

    private Trip tripBuilder(){
        return Trip.builder()
                .id("123")
                .userId("123")
                .country("Brazil")
                .state("Amazonas")
                .city("Manaus")
                .tripName("Aí sim")
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now())
                .observations("Muito quente")
                .build();
    }

    private TripCreateDTO tripDTOBuilder(){
        return TripCreateDTO.builder()
                .userId("123")
                .country("teste")
                .state("teste")
                .city("teste")
                .tripName("teste")
                .observations("teste")
                .build();
    }
}
