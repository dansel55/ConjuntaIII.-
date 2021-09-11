package ec.edu.espe.examen3.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransaccionDto {
    private String idCajero;
    private Integer montoTransaccion;
    private Integer montoBilletes10;
    private Integer montoBilletes20;
    private String tipoTransaccion;
}
