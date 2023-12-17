package mxhtechnology.travelhelptripapp.infrastructure.aws.dynamodb.repository;

import mxhtechnology.travelhelptripapp.entity.Trip;

public interface TripRepository {

    String save(Trip trip);

    Trip findById(String id, String userId);
}
