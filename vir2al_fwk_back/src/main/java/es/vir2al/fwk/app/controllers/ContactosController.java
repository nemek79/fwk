package es.vir2al.fwk.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.session.RowBounds;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.vir2al.fwk.app.domain.ContactoVO;
import es.vir2al.fwk.app.domain.requests.ContactoRequest;
import es.vir2al.fwk.app.services.ContactosService;
import es.vir2al.fwk.fwk.domain.requests.NavigationInfoRequest;
import es.vir2al.fwk.fwk.domain.responses.BaseResponse;
import es.vir2al.fwk.fwk.domain.responses.DataResponse;
import es.vir2al.fwk.fwk.domain.responses.DataTableResponse;
import es.vir2al.fwk.fwk.exceptions.BaseException;
import es.vir2al.fwk.fwk.utils.ListUtils;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/contactos")
public class ContactosController {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactosController.class);

    @Autowired
    private ContactosService contactosService;

    @Autowired
	private ObjectMapper objMapper;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<DataResponse<ContactoVO>> getContactoById(@PathVariable Integer id) {

        LOGGER.debug("INICIO contactos.getContactoById()");

        DataResponse<ContactoVO> response = new DataResponse<ContactoVO>();

        try {

            response.setData(this.contactosService.getContactoById(id));
        
        } catch (BaseException be) {

			LOGGER.error("{} - CODE: {}", be.getMessage(), be.getCode());
			response.setCode(be.getCode());

		} catch (Exception e) {

			LOGGER.error(e.getMessage());
			response.setCode(ResponseConstants.UNEXPECTED_ERROR);

		}

        LOGGER.debug("FINAL contactos.getContactoById()");

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

	@GetMapping()
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> getContactos(@RequestParam Map<String, String> params, NavigationInfoRequest nav) {

        LOGGER.info("PARAMETROS {}", params);
		LOGGER.info("NAVIGATION {}", nav);

        DataTableResponse<ContactoVO> response = new DataTableResponse<ContactoVO>();
        List<ContactoVO> lstData = null;
        Integer total = 0;
        ContactoRequest criteria = new ContactoRequest();

		RowBounds rb = (nav != null && nav.getRows() > 0) ? new RowBounds(nav.getFirst(), nav.getRows())
				: new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);

        try {
            
            criteria = this.objMapper.convertValue(params, ContactoRequest.class);

            lstData = this.contactosService.getContactos(criteria, nav, rb);

            if (!ListUtils.isEmpty(lstData)) {

                total = this.contactosService.getContactosCount(criteria);

            }

            response.setCode(ResponseConstants.RESPONSE_OK);
            response.setData(lstData);
            response.setTotal(total);

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
	public ResponseEntity<?> createContacto(@Valid @RequestBody ContactoVO iVo) {

        LOGGER.info("PARAMETROS {}", iVo);

        DataResponse<ContactoVO> response = new DataResponse<ContactoVO>();
        ContactoVO data = null;

        try {
            
            data = this.contactosService.createContacto(iVo);
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
	public ResponseEntity<?> updateContacto(@PathVariable Integer id,@Valid @RequestBody ContactoVO iVo) {

        LOGGER.info("PARAMETROS {} - {}",id,iVo);

        BaseResponse response = new BaseResponse();

        
        try {
            
            this.contactosService.updateContacto(id, iVo);

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
