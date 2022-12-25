package es.vir2al.fwk.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.vir2al.fwk.fwk.domain.responses.BaseResponse;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/test")
public class TestController {
   
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/ping")
	public ResponseEntity<BaseResponse> ping() {

        LOGGER.debug("INICIO test.ping()");

        BaseResponse baseResponse = new BaseResponse();

        LOGGER.debug("FINAL test.ping()");

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);

    }
    
}
