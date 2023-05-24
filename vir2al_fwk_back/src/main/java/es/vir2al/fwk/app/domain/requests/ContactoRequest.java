package es.vir2al.fwk.app.domain.requests;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import es.vir2al.fwk.fwk.utils.constants.GeneralConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @AllArgsConstructor @ToString
public class ContactoRequest implements Serializable {
    
    private String nombre;
    private String apellidos;
    @JsonFormat(pattern = GeneralConstants.DATE_FORMAT_STRING)
    private Date fechaNacimiento;

    public ContactoRequest() {

        this.nombre = null;
        this.apellidos = null;
        this.fechaNacimiento = null;

    }

}
