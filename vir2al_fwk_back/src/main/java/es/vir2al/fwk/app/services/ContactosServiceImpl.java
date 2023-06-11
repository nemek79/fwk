package es.vir2al.fwk.app.services;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vir2al.fwk.app.domain.ContactoVO;
import es.vir2al.fwk.app.domain.PaisVO;
import es.vir2al.fwk.app.domain.requests.ContactoRequest;
import es.vir2al.fwk.app.repositories.ContactosDAO;
import es.vir2al.fwk.app.repositories.PaisesDAO;
import es.vir2al.fwk.fwk.domain.requests.NavigationInfoRequest;
import es.vir2al.fwk.fwk.exceptions.BaseException;
import es.vir2al.fwk.fwk.utils.StringUtils;
import es.vir2al.fwk.fwk.utils.constants.ResponseConstants;


@Service("contactosService")
public class ContactosServiceImpl implements ContactosService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactosServiceImpl.class);

    @Autowired
    private ContactosDAO contactosDAO;

    @Autowired
    private PaisesDAO paisesDAO;

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
    
    @Override
    public ContactoVO createContacto(ContactoVO data) throws BaseException {

        this.contactosDAO.createContacto(data);

        return data;
    }

    @Override
    public void updateContacto(Integer id, ContactoVO data) throws BaseException {
    
        if (id == null || id <=0 || data == null || data.getId() != id) {
            throw new BaseException(ResponseConstants.INPUT_DATA_ERROR, "El objeto a actualizar no es correcto.");
        }
    
        this.contactosDAO.updateContacto(id, data);
        
    }

    @Override
    public void explode(ContactoVO contacto) throws BaseException {
        
        List<PaisVO> listaPaisesBD = null;

        if (contacto == null)
            throw new BaseException(ResponseConstants.INPUT_DATA_ERROR, "El objeto a explosionar no es correcto.");

        if (!StringUtils.isEmpty(contacto.getPaisNacimiento().getNombre())) {

            listaPaisesBD = this.paisesDAO.getPaisesByNombre(contacto.getPaisNacimiento().getNombre());

            if (listaPaisesBD.size() == 1) {

                contacto.setPaisNacimiento(listaPaisesBD.get(0));

            } else {

                contacto.setPaisNacimiento(null);

            }

        }

        listaPaisesBD = null;

        if (!StringUtils.isEmpty(contacto.getPaisResidencia().getNombre())) {

            listaPaisesBD = this.paisesDAO.getPaisesByNombre(contacto.getPaisResidencia().getNombre());

            if (listaPaisesBD.size() == 1) {

                contacto.setPaisResidencia(listaPaisesBD.get(0));

            } else {

                contacto.setPaisResidencia(null);

            }           

        }

    }

}
