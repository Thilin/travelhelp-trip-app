package mxhtechnology.travelhelptripapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        BDDMockito.given(tripService.createTrip(Mockito.any(TripCreateDTO.class))).willReturn("blablablabla");
        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(TRIP_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk());
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
