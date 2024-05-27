package uniandes.edu.co.demo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Cuenta;

public interface CuentaRepository extends MongoRepository<Cuenta, ObjectId> {

    @Query("{_id: ?0}")
    List<Cuenta> buscarPorId(ObjectId id);

    @Query("{_id: ?0}")
    @Update("{$set:{estado:'cerrado'}}")
    void cambiarEstadoACerrado(ObjectId id_cuenta);

    @Query(value = "{_id: ?0}")
    @Update("{$set:{saldo: ?1}}")
    void actualizarSaldo(ObjectId id_cuenta, float nuevoSaldo);
}