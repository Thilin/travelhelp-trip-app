package mxhtechnology.travelhelptripapp.infrastructure.aws.dynamodb.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import mxhtechnology.travelhelptripapp.entity.Trip;
import mxhtechnology.travelhelptripapp.infrastructure.aws.dynamodb.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class TripRepositoryImpl implements TripRepository {


    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);

    @Override
    public String save(Trip trip) {
        mapper.save(trip);
        return trip.getId();
    }

    @Override
    public Trip findById(String id, String userId) {
        return mapper.load(Trip.class, id, userId);
    }
}
