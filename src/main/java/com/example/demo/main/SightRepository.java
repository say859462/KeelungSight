package com.example.demo.main;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SightRepository extends MongoRepository<Sight,String> {
    ArrayList<Sight> findByZone(String zone);
    Sight findBySightName(String name);
}
