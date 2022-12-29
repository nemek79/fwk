package es.vir2al.fwk.app.services;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vir2al.fwk.app.domain.ContactoVO;
import es.vir2al.fwk.app.domain.requests.ContactoRequest;
import es.vir2al.fwk.app.repositories.ContactosDAO;
import es.vir2al.fwk.fwk.domain.requests.NavigationInfoRequest;
import es.vir2al.fwk.fwk.exceptions.BaseException;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;


@Service("contactosService")
public class ContactosServiceImpl implements ContactosService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactosServiceImpl.class);

    @Autowired
    private ContactosDAO contactosDAO;

    @Override
    public ContactoVO getContactoById(Integer id) throws BaseException {
        
        if (id == null || id < 0) {
            LOGGER.error("INPUT DATA ERROR");
            throw new BaseException(ResponseConstants.INPUT_DATA_ERROR);
        }
        
        return this.contactosDAO.getContactoById(id);
    
    }

    @Override
    public List<ContactoVO> getContactos(ContactoRequest criteria, NavigationInfoRequest navigation, RowBounds rb)
            throws BaseException {
        
        if (criteria == null) {
            LOGGER.error("INPUT DATA ERROR");
            throw new BaseException(ResponseConstants.INPUT_DATA_ERROR);            
        }

        return this.contactosDAO.getContactos(criteria, navigation, rb);
    }

    @Override
    public Integer getContactosCount(ContactoRequest criteria) throws BaseException {

        if (criteria == null) {
            LOGGER.error("INPUT DATA ERROR");
            throw new BaseException(ResponseConstants.INPUT_DATA_ERROR);            
        }

        return this.contactosDAO.getContactosCount(criteria);

    }
    
}
