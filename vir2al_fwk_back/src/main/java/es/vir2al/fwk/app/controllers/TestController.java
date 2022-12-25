package es.vir2al.fwk.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.vir2al.fwk.fwk.domain.responses.BaseResponse;
import es.vir2al.fwk.fwk.domain.responses.DataResponse;
import es.vir2al.fwk.fwk.domain.responses.DataTableResponse;

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

    @GetMapping("/data")
    public ResponseEntity<DataResponse<String>> data() {

        LOGGER.debug("INICIO test.data()");

        String data = "Esto es una cadena de respuesta.";

        DataResponse<String> dataResponse = new DataResponse<String>(data);

        LOGGER.debug("FINAL test.data()");

        return new ResponseEntity<DataResponse<String>>(dataResponse,HttpStatus.OK);

    }

    @GetMapping("/table")
    public ResponseEntity<DataTableResponse<String>> table() {

        LOGGER.debug("INICIO test.table()");

        List<String> data = new ArrayList<String>();

        data.add("DATO 1");
        data.add("DATO 2");
        data.add("DATO 3");
        data.add("DATO 4");

        DataTableResponse<String> dataTableResponse = new DataTableResponse<String>(data,4);

        LOGGER.debug("FINAL test.table()");

        return new ResponseEntity<DataTableResponse<String>>(dataTableResponse,HttpStatus.OK);

    }
    
}
