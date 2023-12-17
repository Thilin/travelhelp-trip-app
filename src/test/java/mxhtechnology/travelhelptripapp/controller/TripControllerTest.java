package mxhtechnology.travelhelptripapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mxhtechnology.travelhelptripapp.dto.TripByIdDTO;
import mxhtechnology.travelhelptripapp.dto.TripCreateDTO;
import mxhtechnology.travelhelptripapp.service.TripService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = TripController.class)
@AutoConfigureMockMvc
public class TripControllerTest {

    static String TRIP_API = "/api/trip";

    @Autowired
    MockMvc mvc;

    @MockBean
    TripService tripService;

    @Test
    @DisplayName("Create a new trip")
    public void createTripTest() throws Exception{
        TripCreateDTO dto = tripBuilder();

        BDDMockito.given(tripService.createTrip(Mockito.any(TripCreateDTO.class))).willReturn("123");
        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(TRIP_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should throw an error when there is not enough data to create a trip")
    public void createInvalidTripTest() throws Exception{
        String json = new ObjectMapper().writeValueAsString(new TripCreateDTO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(TRIP_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should get a Trip by Id")
    public void getTripById() throws Exception{
        var getTrip = TripByIdDTO.builder()
                .country("teste")
                .state("teste")
                .city("teste")
                .tripName("teste")
                .observations("teste")
                .build();

        BDDMockito.given(tripService.findById(Mockito.anyString(), Mockito.anyString())).willReturn(getTrip);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(TRIP_API+"?id=3c8bd703-6c42-4853-8b08-e79744cc89b8&userId=1412-12512sanv")
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return NOT FOUND when the trip does not to exist")
    public void tripNotFoundTest() throws Exception{

        BDDMockito.given(tripService.findById(Mockito.anyString(), Mockito.anyString())).willReturn(null);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(TRIP_API+"?id=3c8bd703-6c42-4853-8b08-e79744cc89b8&userId=1412-12512sanv")
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

    private TripCreateDTO tripBuilder(){
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
