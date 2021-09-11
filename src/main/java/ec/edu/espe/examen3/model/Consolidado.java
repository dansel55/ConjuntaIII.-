package ec.edu.espe.examen3.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document
@Data
@Builder
public class Consolidado {
    @Id
    private String id;
    private Date fechaConsolidacion;
    private Integer montoDisponible;
    private Integer montoBilletes10;
    private Integer montoBilletes20;
}
