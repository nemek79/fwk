package es.vir2al.fwk.app.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import es.vir2al.fwk.fwk.domain.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class ContactoVO extends BaseVO {
   
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombre;

    private String apellidos;

    private PaisVO paisNacimiento;

    private PaisVO paisResidencia;

    public ContactoVO() {

        super();
        this.nombre = null;
        this.apellidos = null;
        this.paisNacimiento = new PaisVO();
        this.paisResidencia = new PaisVO();

    }
    
}
