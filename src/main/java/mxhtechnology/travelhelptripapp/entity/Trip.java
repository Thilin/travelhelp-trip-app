package mxhtechnology.travelhelptripapp.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "travelhelp-trip")
public class Trip {

    @DynamoDBHashKey(attributeName = "id")
    private String id;
    @DynamoDBRangeKey(attributeName = "user_id")
    private String userId;
    //@DynamoDBAttribute(attributeName = "country")
    private String country;
    //@DynamoDBAttribute(attributeName = "state")
    private String state;
    //@DynamoDBAttribute(attributeName = "city")
    private String city;
    //@DynamoDBAttribute(attributeName = "trip_name")
    private String tripName;
    //@DynamoDBAttribute(attributeName = "date_start")
    private String dateStart;
    //@DynamoDBAttribute(attributeName = "date_end")
    private String dateEnd;
    //@DynamoDBAttribute(attributeName = "observations")
    private String observations;
    private List<Itinerary> itineraries;
}
