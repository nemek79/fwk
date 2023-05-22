package es.vir2al.fwk.app.services;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import es.vir2al.fwk.app.domain.PaisVO;
import es.vir2al.fwk.app.domain.requests.PaisRequest;
import es.vir2al.fwk.fwk.domain.requests.NavigationInfoRequest;
import es.vir2al.fwk.fwk.exceptions.BaseException;

public interface PaisesService {
    
    /**
     * Devuelve un pais a partir de su identificador interno
     * @param id
     * @return un pais. Devuelve nulo en caso de no existir.
     * @throws BaseException
     */
    public PaisVO getPaisById(Integer id) throws BaseException;

    /**
     * Devuelve los paises que coincidan con los criterios de búsqueda
     * @param criteria
     * @param navigation
     * @param rb
     * @return Lista de paises. Si ningún pais coincide con los criterios devuelve una lista vacía.
     * @throws BaseException
     */
    public List<PaisVO> getPaises(PaisRequest criteria,  NavigationInfoRequest navigation, RowBounds rb) throws BaseException;

    /**
     * Devuelve el número de paises total que coinciden con los criterios de búsqueda
     * @param criteria
     * @return Número entero
     * @throws BaseException
     */
    public Integer getPaisesCount(PaisRequest criteria) throws BaseException;

}
