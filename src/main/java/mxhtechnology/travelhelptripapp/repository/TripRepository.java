package mxhtechnology.travelhelptripapp.repository;

import mxhtechnology.travelhelptripapp.entity.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TripRepository extends MongoRepository<Trip, String> {
}
