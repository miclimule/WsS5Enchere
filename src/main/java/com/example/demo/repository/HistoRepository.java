package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.collection.HistoCollection;

@Repository
public interface HistoRepository extends MongoRepository<HistoCollection, String> {
   
}