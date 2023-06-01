package es.vir2al.fwk.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.vir2al.fwk.fwk.domain.responses.DataResponse;
import es.vir2al.fwk.fwk.utils.FwkPasswordUtils;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/password")
public class PasswordController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordController.class);

    @GetMapping("/{passwordIn}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<DataResponse<String>> getEncodedPassword(@PathVariable String passwordIn) {

        DataResponse<String> response = new DataResponse<String>();

        response.setData(FwkPasswordUtils.encodePassword(passwordIn));

        return new ResponseEntity<>(response,HttpStatus.OK);

    }


}
