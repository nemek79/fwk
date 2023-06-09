package es.vir2al.fwk.fwk.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class FileVO {
    
    private String name;
    private String url;

    public FileVO() {
        this.name = null;
        this.url = null;
    }

}
