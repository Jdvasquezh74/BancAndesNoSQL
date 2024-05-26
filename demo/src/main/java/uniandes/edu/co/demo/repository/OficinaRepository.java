package uniandes.edu.co.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Oficina;

public interface OficinaRepository extends MongoRepository<Oficina, Integer> {
    @Query("{ '_id' : ?0 }")
    List<Oficina> findById(int id);

    @Query(value = "{}", fields = "{ '_id' : 1 }")
    List<Oficina> findAllIds();
}
