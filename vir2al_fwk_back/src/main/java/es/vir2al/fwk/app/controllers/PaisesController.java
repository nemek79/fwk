package es.vir2al.fwk.app.controllers;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.vir2al.fwk.app.domain.PaisVO;
import es.vir2al.fwk.app.domain.requests.PaisRequest;
import es.vir2al.fwk.app.entities.Pais;
import es.vir2al.fwk.app.services.PaisesService;
import es.vir2al.fwk.fwk.domain.requests.NavigationInfoRequest;
import es.vir2al.fwk.fwk.domain.responses.DataResponse;
import es.vir2al.fwk.fwk.domain.responses.DataTableResponse;
import es.vir2al.fwk.fwk.exceptions.BaseException;
import es.vir2al.fwk.fwk.utils.ListUtils;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/paises")
public class PaisesController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PaisesController.class);

    @Autowired
	private ObjectMapper objMapper;

    @Autowired
    private PaisesService paisesService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<DataResponse<PaisVO>> getContactoById(@PathVariable Integer id) {

        LOGGER.debug("INICIO contactos.getContactoById()");

        DataResponse<PaisVO> response = new DataResponse<PaisVO>();

        try {

            response.setData(this.paisesService.getPaisById(id));
        
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
	public ResponseEntity<?> getPaises(@RequestParam Map<String, String> params, NavigationInfoRequest nav) {

        LOGGER.info("PARAMETROS {}", params);
		LOGGER.info("NAVIGATION {}", nav);

        DataTableResponse<PaisVO> response = new DataTableResponse<PaisVO>();
        List<PaisVO> lstData = null;
        Integer total = 0;
        PaisRequest criteria = new PaisRequest();

		RowBounds rb = (nav != null && nav.getRows() > 0) ? new RowBounds(nav.getFirst(), nav.getRows())
				: new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);

        try {
            
            criteria = this.objMapper.convertValue(params, PaisRequest.class);

            lstData = this.paisesService.getPaises(criteria, nav, rb);

            if (!ListUtils.isEmpty(lstData)) {

                total = this.paisesService.getPaisesCount(criteria);

            }

            response.setCode(ResponseConstants.RESPONSE_OK);
            response.setData(lstData);
            response.setTotal(total);

		} catch (BaseException be) {

            be.printStackTrace();

			LOGGER.error(be.getMessage());
			response.setCode(ResponseConstants.NOT_DEFINED);

		} catch (Exception e) {

			LOGGER.error(e.getMessage());
			response.setCode(ResponseConstants.UNEXPECTED_ERROR);

		}

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/hibernate/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<DataResponse<Pais>> getContactoByIdHibernate(@PathVariable Integer id) {

        LOGGER.debug("INICIO contactos.getContactoById()");

        DataResponse<Pais> response = new DataResponse<Pais>();

        try {
            response.setData(this.paisesService.getPaisByIdHibernate(id));
        
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

}
