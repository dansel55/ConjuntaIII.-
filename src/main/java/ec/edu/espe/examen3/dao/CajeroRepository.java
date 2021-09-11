package ec.edu.espe.examen3.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ec.edu.espe.examen3.model.Cajero;

public interface CajeroRepository extends MongoRepository<Cajero, String> {
    public List<Cajero> findByMontoTotalLessThan(Integer montoTotal);
}
