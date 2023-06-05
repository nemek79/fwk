package es.vir2al.fwk.app.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class TestVO {
    
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String strKey;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer intKey;

}
