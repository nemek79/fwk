package es.vir2al.fwk.app.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import es.vir2al.fwk.fwk.domain.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class PaisVO extends BaseVO {
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombre;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String iso3166a2;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String iso3166a3;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String iso3166num;

    private String domain;

    public PaisVO() {

        super();

        this.id = null;
        this.iso3166a2 = null;
        this.iso3166a3 = null;
        this.iso3166num = null;
        this.domain = null;

    }

}
