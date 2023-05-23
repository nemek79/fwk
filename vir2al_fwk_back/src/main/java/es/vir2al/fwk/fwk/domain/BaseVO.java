package es.vir2al.fwk.fwk.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class BaseVO implements Serializable {
    
    protected Integer id;

    public BaseVO() {
        this.id = null;
    }

}
