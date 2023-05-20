package es.vir2al.fwk.app.services;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import es.vir2al.fwk.app.domain.ContactoVO;
import es.vir2al.fwk.app.domain.requests.ContactoRequest;
import es.vir2al.fwk.fwk.domain.requests.NavigationInfoRequest;
import es.vir2al.fwk.fwk.exceptions.BaseException;

public interface ContactosService {

    /**
     * Devuelve un contacto a partir de su identificador
     * @param id
     * @return un contacto. Devuelve nulo en caso de no existir.
     * @throws BaseException
     */
    public ContactoVO getContactoById(Integer id) throws BaseException;

    /**
     * Devuelve los contactos que coincidand con los criterios de búsqueda
     * @param criteria
     * @param navigation
     * @param rb
     * @return Lista de contactos. Si ningún contacto coincide con los criterios devuelve una lista vacía.
     * @throws BaseException
     */
    public List<ContactoVO> getContactos(ContactoRequest criteria,  NavigationInfoRequest navigation, RowBounds rb) throws BaseException;

    /**
     * Devuelve el número de contactos total que coinciden con los criterios de búsqueda
     * @param criteria
     * @return Número entero
     * @throws BaseException
     */
    public Integer getContactosCount(ContactoRequest criteria) throws BaseException;

    /**
     * Crea un nuevo contacto
     * @param contacto
     * @return
     * @throws BaseException
     */
    public ContactoVO createContacto(ContactoVO data) throws BaseException;
}
