package es.vir2al.fwk.app.services;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vir2al.fwk.app.domain.PaisVO;
import es.vir2al.fwk.app.domain.requests.PaisRequest;
import es.vir2al.fwk.app.entities.Pais;
import es.vir2al.fwk.app.repositories.PaisesDAO;
import es.vir2al.fwk.app.repositories.hibernate.PaisesRepository;
import es.vir2al.fwk.fwk.domain.requests.NavigationInfoRequest;
import es.vir2al.fwk.fwk.exceptions.BaseException;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;

@Service("paisesService")
public class PaisesServiceImpl implements PaisesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaisesServiceImpl.class);

    @Autowired
    private PaisesDAO paisesDAO;

    @Autowired
    private PaisesRepository paisesRepository;

    @Override
    public PaisVO getPaisById(Integer id) throws BaseException {

        if (id == null || id < 0) {
            LOGGER.error("INPUT DATA ERROR");
            throw new BaseException(ResponseConstants.INPUT_DATA_ERROR);
        }
        
        return this.paisesDAO.getPaisById(id);

    }

    @Override
    public List<PaisVO> getPaises(PaisRequest criteria, NavigationInfoRequest navigation, RowBounds rb)
            throws BaseException {

        if (criteria == null) {
            LOGGER.error("INPUT DATA ERROR");
            throw new BaseException(ResponseConstants.INPUT_DATA_ERROR);            
        }

        return this.paisesDAO.getPaises(criteria, navigation, rb);

    }

    @Override
    public Integer getPaisesCount(PaisRequest criteria) throws BaseException {

        if (criteria == null) {
            LOGGER.error("INPUT DATA ERROR");
            throw new BaseException(ResponseConstants.INPUT_DATA_ERROR);            
        }

        return this.paisesDAO.getPaisesCount(criteria);

    }

    @Override
    public Pais getPaisByIdHibernate(Integer id) throws BaseException {
        
        if (id == null || id < 0) {
            LOGGER.error("INPUT DATA ERROR");
            throw new BaseException(ResponseConstants.INPUT_DATA_ERROR);
        }       

        return this.paisesRepository.findById(id).orElseThrow(()
            -> new BaseException(ResponseConstants.NODATA_FOUND_ERROR, 
                                "No se ha encontrado objeto con id: "+id));           
    }
    
}
