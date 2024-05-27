package uniandes.edu.co.demo.repository;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Oficina;

public interface OficinaRepository extends MongoRepository<Oficina, ObjectId> {
    @Query("{ '_id' : ?0 }")
    List<Oficina> findById(int id);

    @Query(value = "{}", fields = "{ '_id' : 1 }")
    List<Oficina> findAllIds();


    
    @Query("{'_id': ?0}")
    @Update("{$push:{'puntosatencion': {"
        +"'_id': ?1, "
        + "'tipo': ?2, "
        + "'latitud': ?3, "
        + "'longitud': ?4, "
        + "'operaciones': ?5"
        + "}}}")
    void anadirPuntoAtencion(ObjectId idOficina, ObjectId idpunto,String tipo, Float latitud, Float longitud, List<String> operaciones);

    @Query(value = "{'puntosatencion': {$exists: true}}", fields = "{'puntosatencion._id': 1}")
    List<ObjectId> findAllPuntosAtencionIds();


    @Query("{}")
    @Update("{$pull: {'puntosatencion': {'_id': ?0}}}")
    void deletePuntoAtencionById(ObjectId puntoAtencionId);


}
