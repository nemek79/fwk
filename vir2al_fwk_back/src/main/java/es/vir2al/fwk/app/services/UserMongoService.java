package es.vir2al.fwk.app.services;

import es.vir2al.fwk.app.entities.documents.UserDocument;
import es.vir2al.fwk.fwk.exceptions.BaseException;

public interface UserMongoService {
    
    public UserDocument createUser(UserDocument user) throws BaseException;

    public UserDocument getUser(String login) throws BaseException;

}
