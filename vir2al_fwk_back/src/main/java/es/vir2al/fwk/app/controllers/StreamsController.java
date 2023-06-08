package es.vir2al.fwk.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.vir2al.fwk.app.domain.TestVO;
import es.vir2al.fwk.app.services.TestsService;
import es.vir2al.fwk.fwk.domain.responses.BaseResponse;
import es.vir2al.fwk.fwk.exceptions.BaseException;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/streams")
public class StreamsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsController.class);

    @Autowired
    private TestsService testsService;

    @GetMapping("/test01")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<BaseResponse> getContactoById() {

        final BaseResponse response = new BaseResponse();

        LOGGER.info("STREAMS - BEGIN - TEST01");

        try {

            final List<TestVO> lstTests = this.testsService.getTests();

            /*
             * Ejemplo de MAP
             * Cremos una lista con las strKey mayúsculas
             */

             final List<TestVO> lstMayusculas = lstTests.stream()
                                                    .map(
                                                        test -> {
                                                            test.setStrKey(test.getStrKey().toUpperCase());
                                                            return test;
                                                        }
                                                    ).collect(Collectors.toList());

            lstMayusculas.stream().forEach(test -> LOGGER.info(test.getStrKey()));
        
        } catch (BaseException e) {

            e.printStackTrace();
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.info("STREAMS - END - TEST01");

        return new ResponseEntity<>(response,HttpStatus.OK);

    }


    
}
