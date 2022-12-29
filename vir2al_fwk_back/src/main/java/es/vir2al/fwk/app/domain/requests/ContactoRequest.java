package es.vir2al.fwk.app.domain.requests;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class ContactoRequest implements Serializable {
    
    private String nombre;
    private String apellidos;

}
