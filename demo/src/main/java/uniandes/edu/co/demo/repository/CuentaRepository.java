package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;


import uniandes.edu.co.demo.modelo.Cuenta;

public interface CuentaRepository extends MongoRepository<Cuenta, Integer>{

    @Query("{_id: ?0}")
        List<Cuenta> buscarPorId(Integer id);
    @Query("{_id: ?0}")
        @Update("{$set:{estado:'cerrado'}}")
        void cambiarEstadoACerrado(Integer id_cuenta);
}
