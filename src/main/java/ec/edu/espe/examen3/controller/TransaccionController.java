package ec.edu.espe.examen3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.examen3.dto.TransaccionDto;
import ec.edu.espe.examen3.model.Transaccion;
import ec.edu.espe.examen3.service.TransaccionService;

@RestController
@RequestMapping("/api/v1/recepcion")
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/transaccion")
    public ResponseEntity<Transaccion> registrarTransaccion(@RequestBody TransaccionDto transaccionDto) {
        return ResponseEntity.ok().body(this.transaccionService.registrarTransaccion(transaccionDto));
    }
}
