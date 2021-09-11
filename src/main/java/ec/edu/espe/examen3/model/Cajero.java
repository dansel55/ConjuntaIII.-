package ec.edu.espe.examen3.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document
@Data
@Builder
public class Cajero {
    @Id
    private String id;
    private String nombre;
    private String ubicacion;
    private Integer montoTotal;
    private Integer montoBilletes10;
    private Integer montoBilletes20;
}
