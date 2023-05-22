package es.vir2al.fwk.app.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class PaisVO {
    
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombre;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String iso3166a2;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String iso3166a3;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String iso3166num;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String domain;

}
