package es.vir2al.fwk.app.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.vir2al.fwk.app.entities.documents.UserDocument;
import es.vir2al.fwk.app.services.UserMongoService;
import es.vir2al.fwk.fwk.domain.responses.DataResponse;
import es.vir2al.fwk.fwk.exceptions.BaseException;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/mongo")
public class UsersMongoController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersMongoController.class);

    @Autowired
    private UserMongoService userMongoService;

    @GetMapping("/{login}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> getUserById(@PathVariable String login) {
    
        LOGGER.debug("INICIO usersMongo.getUserById()");

        DataResponse<UserDocument> response = new DataResponse<UserDocument>();
        
        try {

            response.setData(this.userMongoService.getUser(login));
        
        } catch (BaseException be) {

			LOGGER.error("{} - CODE: {}", be.getMessage(), be.getCode());
			response.setCode(be.getCode());

		} catch (Exception e) {

			LOGGER.error(e.getMessage());
			response.setCode(ResponseConstants.UNEXPECTED_ERROR);

		} 

        LOGGER.debug("FINAL usersMongo.getUserById()");
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDocument iVo) {

        LOGGER.info("PARAMETROS {}", iVo);

        DataResponse<UserDocument> response = new DataResponse<UserDocument>();
        UserDocument data = null;

        try {
            
            data = this.userMongoService.createUser(iVo);
            response.setData(data);

        } catch (BaseException be) {

			LOGGER.error("{} - CODE: {}",be.getMessage(),be.getCode());
			response.setCode(be.getCode());

        } catch (Exception e) {

			LOGGER.error(e.getMessage());
			response.setCode(ResponseConstants.UNEXPECTED_ERROR);
            
        }
    
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
