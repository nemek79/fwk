package es.vir2al.fwk.app.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class ContactoVO implements Serializable {
   
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombre;

    private String apellidos;

    private PaisVO paisNacimiento;

    private PaisVO paisResidencia;

    public ContactoVO() {

        this.id = null;
        this.nombre = null;
        this.apellidos = null;
        this.paisNacimiento = new PaisVO();
        this.paisResidencia = new PaisVO();

    }
    
}
