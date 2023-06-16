package es.vir2al.fwk.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_paises")
@Getter @Setter @AllArgsConstructor @ToString
public class Pais {
    
    @Id
    private Integer id;

    private String nombre;

    @Column(name = "iso_3166_a2")
    private String iso3166a2;
    @Column(name = "iso_3166_a3")
    private String iso3166a3;
    @Column(name = "iso_3166_num")
    private String iso3166num;

    private String domain;

    public Pais(){}

}
