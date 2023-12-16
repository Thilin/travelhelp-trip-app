package mxhtechnology.travelhelptripapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripCreateDTO {

    @Schema(example = "1412-12512sanv", description = "the user id")
    @NotEmpty
    private String userId;

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
    private LocalDate dateStart;

    @Schema(example = "23/02/2024", description = "The end date of the trip")
    private LocalDate dateEnd;

    @Schema(example = "This will be the trip that I always wanted to do", description = "Any type of description")
    @NotEmpty
    private String observations;
}
