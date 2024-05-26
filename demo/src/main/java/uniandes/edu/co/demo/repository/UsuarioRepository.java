package uniandes.edu.co.demo.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Usuario;

import java.util.Optional;
public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {
     @Query("{_id: ?0}")
    Optional<Usuario> findById(ObjectId id);
}