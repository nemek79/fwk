package es.vir2al.fwk.app.repositories.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;

import es.vir2al.fwk.app.entities.documents.UserDocument;

public interface UserMongoRepository extends MongoRepository<UserDocument,String>{
    
}
