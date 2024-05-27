package uniandes.edu.co.demo.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Usuario;
import java.util.Optional;
public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {
     @Query("{ '_id' : ?0 }")
    Optional<Usuario> findById(ObjectId id);

    @Query("{'_id': ?0}")
    @Update("{$push:{'cuentas': ?1}}")
    void anadirCuenta(ObjectId idUsuario, ObjectId idCuenta);
    


}