package ec.edu.espe.examen3.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ec.edu.espe.examen3.model.Transaccion;

public interface TransaccionRepository extends MongoRepository<Transaccion, String> {
    public List<Transaccion> findByConsolidado(Boolean consolidado);
}
