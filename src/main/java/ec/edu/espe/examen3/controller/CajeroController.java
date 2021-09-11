package ec.edu.espe.examen3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.examen3.exception.EntityNotFoundException;
import ec.edu.espe.examen3.model.Cajero;
import ec.edu.espe.examen3.service.CajeroService;

@RestController
@RequestMapping("/api/v1/cajero")
public class CajeroController {
    @Autowired
    private CajeroService cajeroService;

    @GetMapping("/{idCajero}")
    public ResponseEntity<Cajero> buscarPorIdCajero(@PathVariable String idCajero) {
        try {
            return ResponseEntity.ok(cajeroService.buscarPorId(idCajero));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/monto/{montoTotal}")
    public ResponseEntity<List<Cajero>> buscarPorMonto(@PathVariable Integer montoTotal) {
        return ResponseEntity.ok(cajeroService.buscarCajerosPorMontoTotal(montoTotal));
    }

}
