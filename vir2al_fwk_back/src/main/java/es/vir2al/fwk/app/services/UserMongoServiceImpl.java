package es.vir2al.fwk.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vir2al.fwk.app.entities.documents.UserDocument;
import es.vir2al.fwk.app.repositories.mongo.UserMongoRepository;
import es.vir2al.fwk.fwk.exceptions.BaseException;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;

@Service("userMongoService")
public class UserMongoServiceImpl implements UserMongoService {

    @Autowired
    private UserMongoRepository userMongoRepository;

    @Override
    public UserDocument createUser(UserDocument user) throws BaseException {

        return this.userMongoRepository.save(user);

    }

    @Override
    public UserDocument getUser(String login) throws BaseException {

        return this.userMongoRepository.findById(login).orElseThrow(()
            -> new BaseException(ResponseConstants.NODATA_FOUND_ERROR, 
                                "No se ha encontrado objeto con id: "+login)); 


    }
    
}
