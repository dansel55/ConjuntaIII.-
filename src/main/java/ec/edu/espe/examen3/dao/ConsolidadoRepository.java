package ec.edu.espe.examen3.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ec.edu.espe.examen3.model.Consolidado;

public interface ConsolidadoRepository extends MongoRepository<Consolidado, String> {
    
}
