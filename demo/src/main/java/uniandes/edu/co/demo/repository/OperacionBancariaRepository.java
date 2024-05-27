package uniandes.edu.co.demo.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.OperacionBancaria;

public interface OperacionBancariaRepository extends MongoRepository<OperacionBancaria,ObjectId> {
     @Query("{ '_id' : ?0 }")
     Optional<OperacionBancaria> findById(ObjectId id);

}

