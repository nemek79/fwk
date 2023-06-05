package es.vir2al.fwk.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.vir2al.fwk.app.domain.TestVO;
import es.vir2al.fwk.app.services.TestsService;
import es.vir2al.fwk.fwk.domain.responses.BaseResponse;
import es.vir2al.fwk.fwk.domain.responses.DataResponse;
import es.vir2al.fwk.fwk.domain.responses.DataTableResponse;
import es.vir2al.fwk.fwk.exceptions.BaseException;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/tests")
public class TestsController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactosController.class);

    @Autowired
    private TestsService testsService;

    @Autowired
	private ObjectMapper objMapper;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<DataResponse<TestVO>> getTestById(@PathVariable Integer id) {

        LOGGER.debug("INICIO tests.getTestById()");

        DataResponse<TestVO> response = new DataResponse<TestVO>();

        try {

            response.setData(this.testsService.getTestById(id));
        
        } catch (BaseException be) {

			LOGGER.error("{} - CODE: {}", be.getMessage(), be.getCode());
			response.setCode(be.getCode());

		} catch (Exception e) {

			LOGGER.error(e.getMessage());
			response.setCode(ResponseConstants.UNEXPECTED_ERROR);

		}

        LOGGER.debug("FINAL tests.getTestById()");

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

	@GetMapping()
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> getTests() {

        DataTableResponse<TestVO> response = new DataTableResponse<TestVO>();
        List<TestVO> lstData = null;

        try {
            
            lstData = this.testsService.getTests();
            response.setData(lstData);

		} catch (BaseException be) {

			LOGGER.error(be.getMessage());
			response.setCode(ResponseConstants.NOT_DEFINED);

		} catch (Exception e) {

			LOGGER.error(e.getMessage());
			response.setCode(ResponseConstants.UNEXPECTED_ERROR);

		}

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> createTest(@Valid @RequestBody TestVO iVo) {

        LOGGER.info("PARAMETROS {}", iVo);

        DataResponse<TestVO> response = new DataResponse<TestVO>();
        TestVO data = null;

        try {
            
            data = this.testsService.createTest(iVo);
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

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> updateTest(@PathVariable Integer id,@Valid @RequestBody TestVO iVo) {

        LOGGER.info("PARAMETROS {} - {}",id,iVo);

        BaseResponse response = new BaseResponse();

        
        try {
            
            this.testsService.updateTest(id, iVo);

        } catch (BaseException be) {

			LOGGER.error("{} - CODE: {}",be.getMessage(),be.getCode());
			response.setCode(be.getCode());

        } catch (Exception e) {

			LOGGER.error(e.getMessage());
			response.setCode(ResponseConstants.UNEXPECTED_ERROR);
            
        }
    
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> deleteTest(@PathVariable Integer id) {

        LOGGER.info("PARAMETROS {}",id);

        BaseResponse response = new BaseResponse();

        
        try {
            
            this.testsService.deleteTestById(id);
        } catch (BaseException be) {

			LOGGER.error("{} - CODE: {}",be.getMessage(),be.getCode());
			response.setCode(be.getCode());

        } catch (Exception e) {

			LOGGER.error(e.getMessage());
			response.setCode(ResponseConstants.UNEXPECTED_ERROR);
            
        }
    
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

	@PostMapping("/masivo")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> createTests() {

        BaseResponse response = new BaseResponse();
        
        try {
            
            this.testsService.createTestsMasivo();

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
