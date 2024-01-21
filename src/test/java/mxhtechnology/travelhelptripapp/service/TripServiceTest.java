package mxhtechnology.travelhelptripapp.service;

import mxhtechnology.travelhelptripapp.dto.TripCreateDTO;
import mxhtechnology.travelhelptripapp.entity.Trip;
import mxhtechnology.travelhelptripapp.infrastructure.aws.dynamodb.repository.TripRepository;
import mxhtechnology.travelhelptripapp.service.impl.TripServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
    @DisplayName("Should create a new trip")
    public void createTripTest(){
        when(tripRepository.save(any(Trip.class))).thenReturn("aehaue");

        var dto = tripDTOBuilder();
        var tripCreated = tripService.createTrip(dto);

        assertThat(tripCreated).isNotEmpty();
    }

    @Test
    @DisplayName("Should return a trip")
    public void getTripByIdTest(){
        String pk = "123";
        String sk = "123";

        var trip = tripBuilder();

        when(tripRepository.findById(anyString(), anyString())).thenReturn(trip);

        var tripFound = tripService.findById(pk, sk);

        assertThat(tripFound.getTripName()).isEqualTo(trip.getTripName());
    }

    @Test
    @DisplayName("Should return null when trip does not exists")
    public void tripNotFoundTest(){
        String pk = "123";
        String sk = "123";

        when(tripRepository.findById(anyString(), anyString())).thenReturn(null);

        var tripFound = tripService.findById(pk, sk);

        assertThat(tripFound).isNull();
    }

    @Test
    @DisplayName("Should delete a trip")
    public void deleteTripTest(){
        var trip = tripBuilder();

        when(tripRepository.findById(anyString(), anyString())).thenReturn(trip);
        Assertions.assertDoesNotThrow(() -> tripService.deleteTrip(anyString(), anyString()));

        Mockito.verify(tripRepository, Mockito.times(1)).delete(trip);
    }

    @Test
    @DisplayName("Shuold throws an error when trying to delete a trip inexistent")
    public void deleteInvalidTripTest(){
        var trip = new Trip();

        Assertions.assertThrows(IllegalArgumentException.class, () -> tripService.deleteTrip(anyString(), anyString()));

    }



    private Trip tripBuilder(){
        return Trip.builder()
                .id("123")
                .userId("123")
                .country("Brazil")
                .state("Amazonas")
                .city("Manaus")
                .tripName("Aí sim")
                .dateStart("test")
                .dateEnd("test")
                .observations("Muito quente")
                .build();
    }

    private TripCreateDTO tripDTOBuilder(){
        return TripCreateDTO.builder()
                .userId("123")
                .country("teste")
                .state("teste")
                .city("teste")
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now())
                .tripName("teste")
                .observations("teste")
                .build();
    }
}
