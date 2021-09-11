package ec.edu.espe.examen3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.espe.examen3.dao.CajeroRepository;
import ec.edu.espe.examen3.exception.EntityNotFoundException;
import ec.edu.espe.examen3.model.Cajero;

@Service
public class CajeroService {
    @Autowired
    private CajeroRepository cajeroRepository;

    public Cajero buscarPorId(String idCajero) throws EntityNotFoundException {
        Optional<Cajero> cajero = this.cajeroRepository.findById(idCajero);
        if(cajero.isEmpty()) {
            throw new EntityNotFoundException("No se encontro un cajero con el id: " + idCajero);
        }
        return cajero.get();
    }

    public List<Cajero> buscarCajerosPorMontoTotal(Integer montoTotal) {
        return this.cajeroRepository.findByMontoTotalLessThan(montoTotal);
    }
}
