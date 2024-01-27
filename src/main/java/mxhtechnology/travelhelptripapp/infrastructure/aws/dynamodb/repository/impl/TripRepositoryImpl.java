package mxhtechnology.travelhelptripapp.infrastructure.aws.dynamodb.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import mxhtechnology.travelhelptripapp.entity.Trip;
import mxhtechnology.travelhelptripapp.infrastructure.aws.dynamodb.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.management.Attribute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class TripRepositoryImpl implements TripRepository {


    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);

    @Override
    public String save(Trip trip) {
        trip.setId("TRIP#");
        mapper.save(trip);
        return trip.getId();
    }

    @Override
    public Trip findById(String id, String userId) {
        return mapper.load(Trip.class, id, userId);
    }

    @Override
    public void delete(Trip trip) {
        mapper.delete(trip);
    }

    @Override
    public List<Trip> listByUser(String pk, String sk) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":v1", new AttributeValue().withS(pk));
        eav.put(":v2", new AttributeValue().withS(sk));

        DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
                .withKeyConditionExpression("id = :v1 and begins_with(user_id, :v2)")
                .withExpressionAttributeValues(eav);

        return mapper.query(Trip.class, queryExpression);
    }
}
