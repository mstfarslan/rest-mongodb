package com.example.restapi.repository;

import com.example.restapi.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends MongoRepository<City,String> {
    List <City>findAllByName(String name );
    Optional<City>findByName(String name);
}
