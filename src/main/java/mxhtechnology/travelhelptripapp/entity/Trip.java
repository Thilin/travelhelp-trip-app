package mxhtechnology.travelhelptripapp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("trip")
public class Trip {

    @Id
    private String id;
    private String userId;
    private String country;
    private String state;
    private String city;
    private String tripName;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String observations;
}
