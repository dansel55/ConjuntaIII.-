package ec.edu.espe.examen3.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.espe.examen3.dao.TransaccionRepository;
import ec.edu.espe.examen3.dto.TransaccionDto;
import ec.edu.espe.examen3.model.Transaccion;

@Service
public class TransaccionService {
    @Autowired
    private TransaccionRepository transaccionRepository;

    public Transaccion registrarTransaccion(TransaccionDto transaccionDto) {
        Transaccion trasaccion = Transaccion.builder()
            .consolidado(false)
            .fechaTransaccion(new Date())
            .idCajero(transaccionDto.getIdCajero())
            .montoBilletes10(transaccionDto.getMontoBilletes10())
            .montoBilletes20(transaccionDto.getMontoBilletes20())
            .montoTransaccion(transaccionDto.getMontoTransaccion())
            .tipoTransaccion(transaccionDto.getTipoTransaccion())
            .build();
        return this.transaccionRepository.save(trasaccion);
    }
}
