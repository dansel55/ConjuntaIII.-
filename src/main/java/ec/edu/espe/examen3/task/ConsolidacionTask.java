package ec.edu.espe.examen3.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import ec.edu.espe.examen3.config.Configuraciones;
import ec.edu.espe.examen3.dao.CajeroRepository;
import ec.edu.espe.examen3.dao.ConsolidadoRepository;
import ec.edu.espe.examen3.dao.TransaccionRepository;
import ec.edu.espe.examen3.model.Cajero;
import ec.edu.espe.examen3.model.Consolidado;
import ec.edu.espe.examen3.model.Transaccion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsolidacionTask implements Tasklet, StepExecutionListener {

    private final ConsolidadoRepository consolidadoRepository;

    private final TransaccionRepository transaccionRepository;

    private final CajeroRepository cajeroRepository;

    public ConsolidacionTask(ConsolidadoRepository consolidadoRepository, TransaccionRepository transaccionRepository, CajeroRepository cajeroRepository) {
        this.consolidadoRepository = consolidadoRepository;
        this.transaccionRepository = transaccionRepository;
        this.cajeroRepository = cajeroRepository;
    }
    
    @Override
    public void beforeStep(StepExecution arg0) {
        log.info("iniciando el Consolidado");
    }
    
    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
        List<Transaccion> transaccionesPorConsolidar = this.transaccionRepository.findByConsolidado(false);
        Optional<Cajero> cajeroOptional;
        Cajero cajero;
        Consolidado consolidado;
        List<Consolidado> consolidados = new ArrayList<>();
        for (Transaccion transaccion : transaccionesPorConsolidar) {
            cajeroOptional = this.cajeroRepository.findById(transaccion.getIdCajero());
            cajero = cajeroOptional.get();
            if(transaccion.getTipoTransaccion().equals(Configuraciones.TIPO_TRANSACCION.RETIRO.getTipo())) {
                cajero.setMontoTotal(cajero.getMontoTotal() - transaccion.getMontoTransaccion());
                cajero.setMontoBilletes10(cajero.getMontoBilletes10() - transaccion.getMontoBilletes10());
                cajero.setMontoBilletes20(cajero.getMontoBilletes20() - transaccion.getMontoBilletes20());
            } else {
                cajero.setMontoTotal(cajero.getMontoTotal() + transaccion.getMontoTransaccion());
                cajero.setMontoBilletes10(cajero.getMontoBilletes10() + transaccion.getMontoBilletes10());
                cajero.setMontoBilletes20(cajero.getMontoBilletes20() + transaccion.getMontoBilletes20());
            }
            transaccion.setConsolidado(true);
            this.cajeroRepository.save(cajero);
            consolidado = Consolidado.builder()
                    .fechaConsolidacion(new Date())
                    .montoBilletes10(cajero.getMontoBilletes10())
                    .montoBilletes20(cajero.getMontoBilletes20())
                    .montoDisponible(cajero.getMontoTotal())
                    .build();
            consolidados.add(consolidado);
        }
        this.transaccionRepository.saveAll(transaccionesPorConsolidar);
        this.consolidadoRepository.saveAll(consolidados);
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution arg0) {
        return ExitStatus.COMPLETED;
    }
}
