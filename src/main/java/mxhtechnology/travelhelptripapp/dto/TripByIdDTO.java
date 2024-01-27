package mxhtechnology.travelhelptripapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import mxhtechnology.travelhelptripapp.entity.Itinerary;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripByIdDTO {

    @Schema(example = "Brazil", description = "the country that will be the trip's destinatination")
    @NotEmpty
    private String country;

    @Schema(example = "Amazonas", description = "the state/province that will be the trip's destinatination")
    @NotEmpty
    private String state;

    @Schema(example = "Manaus", description = "the city that will be the trip's destinatination")
    @NotEmpty
    private String city;

    @Schema(example = "Viagem dos sonhos", description = "A name for the trip")
    @NotEmpty
    private String tripName;

    @Schema(example = "23/12/2023", description = "The start date of the trip")
    private String dateStart;

    @Schema(example = "23/02/2024", description = "The end date of the trip")
    private String dateEnd;

    @Schema(example = "This will be the trip that I always wanted to do", description = "Any type of description")
    @NotEmpty
    private String observations;

    @Schema(example = "Amazonas Theatre", description = "A List of the itinerary")
    private List<Itinerary> itineraries;
}
