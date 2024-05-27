package uniandes.edu.co.demo.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.OperacionBancaria;

public interface OperacionBancariaRepository extends MongoRepository<OperacionBancaria,ObjectId> {
     @Query("{ '_id' : ?0 }")
     Optional<OperacionBancaria> findById(ObjectId id);

     @Aggregation(pipeline = {
        "{ $match: { 'cuentaafectada': ?0 } }",
        "{ $addFields: { 'fechaoperacionDate': { $dateFromString: { dateString: '$fechaoperacion', format: '%d/%m/%Y %H:%M:%S' } } } }",
        "{ $match: { 'fechaoperacionDate': { $gte: ?1, $lt: ?2 } } }"
    })
    List<OperacionBancaria> findOperacionesByCuentaAndFecha(ObjectId cuentaId, String startDate, String endDate);

}

