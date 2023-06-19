package es.vir2al.fwk.app.entities.documents;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;

@Document(collection = "usuarios")
@Getter @Setter @AllArgsConstructor @ToString
public class UserDocument {
    
    @Id
    private String login;

    private String password;

    private String nombre;

    public UserDocument() {};

}
