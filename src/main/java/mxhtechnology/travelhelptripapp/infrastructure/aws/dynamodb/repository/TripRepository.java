package mxhtechnology.travelhelptripapp.infrastructure.aws.dynamodb.repository;

import mxhtechnology.travelhelptripapp.entity.Trip;

import java.util.List;

public interface TripRepository {

    String save(Trip trip);

    Trip findById(String id, String userId);

    void delete(Trip trip);

    List<Trip> listByUser(String pk, String sk);
}
