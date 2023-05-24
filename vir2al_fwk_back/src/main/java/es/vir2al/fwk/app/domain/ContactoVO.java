package es.vir2al.fwk.app.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import es.vir2al.fwk.fwk.domain.BaseVO;
import es.vir2al.fwk.fwk.utils.constants.GeneralConstants;
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

    @JsonFormat(pattern = GeneralConstants.DATE_FORMAT_STRING)
    private Date fechaNacimiento;

    public ContactoVO() {

        super();
        this.nombre = null;
        this.apellidos = null;
        this.paisNacimiento = new PaisVO();
        this.paisResidencia = new PaisVO();
        this.fechaNacimiento = null;

    }
    
}
